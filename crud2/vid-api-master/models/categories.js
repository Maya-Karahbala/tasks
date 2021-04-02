module.exports = (sequelize, DataTypes) => {
  const Categories = sequelize.define("Categories", {
    id: {
			type: DataTypes.INTEGER(11),
			primaryKey: true,
			autoIncrement: true,
		},
    name: {
      type: DataTypes.STRING,
    
    },
    image: {
      type: DataTypes.STRING,
    
    },
    description: {
      type: DataTypes.STRING,
 
    }
  }, {
		timestamps: false
	});
  return Categories;
};
