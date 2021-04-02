module.exports = (sequelize, DataTypes) => {
  const Subcategories = sequelize.define("Subcategories", {
    id: {
			type: DataTypes.INTEGER(11),
			primaryKey: true,
			autoIncrement: true,
		},
    categories_id: {
			type: DataTypes.INTEGER(11),
			allowNull: false,
			references: {
				model: 'Categories',
				key: 'id'
			},
			field: 'categories_id'
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
		timestamps: false,
 
	}
 
  );
  return Subcategories;
};

