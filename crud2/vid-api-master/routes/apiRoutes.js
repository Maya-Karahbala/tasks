const express = require("express");
const router = express.Router();
const db = require("../models");

// ------------------------------- model relations-------------------
db.Categories.hasMany(db.Subcategories, {
  as: "Subcategories",
  foreignKey: "categories_id"
});
db.Subcategories.belongsTo(db.Categories, {
  as: "Categories",
  foreignKey: "categories_id"
})

// categories and items has many to many relation
db.Categories.belongsToMany(db.Items, {
  through: "itemsCategories",
  foreignKey: "categories_id",

});
db.Items.belongsToMany(db.Categories, {
  through: "itemsCategories",
  foreignKey: "items_id",

});

db.itemsCategories.belongsTo(db.Categories, {
  foreignKey: "categories_id"
});
db.itemsCategories.belongsTo(db.Items, {
  foreignKey: "items_id"
});
 console.log("******************1**************")
 db.Categories.findAll({ include: db.Items }).then((cat) => {
   console.log(cat)
 })
//--------------------------------Categories routes-------------------
// get all categories
router.get("/categories/all", (req, res) => {
  db.Categories.findAll().then((Categories) => res.send(Categories));
});

// get single category by id
router.get("/categories/find/:id", (req, res) => {
  db.Categories.findAll({
    include: ['Subcategories',"Items"],
    where: {
      id: req.params.id,
    },
  }).then((category) => res.send(category));
});

// post new category
router.post("/categories/new", (req, res) => {
  db.Categories.create({
    name: req.body.name,
    image: req.body.image,
    description: req.body.description,
  }).then((submitedCategory) => res.send(submitedCategory));
});

// delete category
router.delete("/categories/delete/:id", (req, res) => {
  db.Categories.destroy({
    where: {
      id: req.params.id,
    },
  }).then(() => res.send("success"));
});

// edit a category
router.put("/categories/edit", (req, res) => {
  console.log(req.body)
  console.log("----------------------------------------")
  db.Categories.update(
    {
      name: req.body.name,
      image: req.body.image,
      description: req.body.description,
    },
    {
      where: { id: req.body.id },
    }
  ).then(() => res.send("success categories/edit"));
});


//--------------------------------Subcategories routes-------------------
// get all subcategories
router.get("/subcategories/all", (req, res) => {
  db.Subcategories.findAll().then((Categories) => res.send(Categories));
});

// get single subcategory by id
router.get("/subcategories/find/:id", (req, res) => {
  db.Subcategories.findAll({
    include: ['Categories'],
    where: {
      id: req.params.id,
    },
  }).then((category) => res.send(category));
});

// post new subcategory
router.post("/subcategories/new", (req, res) => {
  db.Subcategories.create({
   name: req.body.name,
    image: req.body.image,
    description: req.body.description,
    categories_id:req.body.categories_id
  }).then((submitedCategory) => res.send(submitedCategory));
});

// delete subcategory
router.delete("/subcategories/delete/:id", (req, res) => {
  db.Subcategories.destroy({
    where: {
      id: req.params.id,
    },
  }).then(() => res.send("success"));
});

// edit a subcategory
router.put("/subcategories/edit", (req, res) => {
  db.Subcategories.update(
    {
      name: req.body.name,
      image: req.body.image,
      description: req.body.description,
      categories_id:req.body.categories_id
    },
    {
      where: { id: req.body.id },
    }
  ).then(() => res.send("success"));
});
//--------------------------------items routes-------------------
// get all subcategories
router.get("/items/all", (req, res) => {
  db.Items.findAll().then((items) => res.send(items));
});

// get single subcategory by id
router.get("/items/find/:id", (req, res) => {
  db.Items.findAll({
    include: ['Categories'],
    where: {
      id: req.params.id,
    },
  }).then((items) => res.send(items));
});

// post new items
router.post("/items/new", (req, res) => {
  db.Items.create({
    image: req.body.image,
    text_editor: req.body.text_editor,
    gallery:req.body.gallery
  }).then((submitedItem) => res.send(submitedItem));
});

// delete items
router.delete("/items/delete/:id", (req, res) => {
  db.Items.destroy({
    where: {
      id: req.params.id,
    },
  }).then(() => res.send("success"));
});

// edit an items
router.put("/items/edit", (req, res) => {
  db.Items.update(
    {
      image: req.body.image,
      text_editor: req.body.text_editor,
      gallery:req.body.gallery
    },
    {
      where: { id: req.body.id },
    }
  ).then(() => res.send("success"));
});
module.exports = router;
