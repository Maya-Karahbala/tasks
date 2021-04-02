
module.exports = (sequelize, DataTypes) => {
	const itemsCategories = sequelize.define("itemsCategories", {
		id: {
			type: DataTypes.INTEGER(11),
			allowNull: false,
			primaryKey: true,
			autoIncrement: true,
			field: 'id'
		},
		items_id: {
			type: DataTypes.INTEGER(11),
			allowNull: false,
			references: {
				model: 'Items',
				key: 'id'
			},
			field: 'items_id'
		},
		categories_id: {
			type: DataTypes.INTEGER(11),
			allowNull: false,
			references: {
				model: 'Categories',
				key: 'id'
			},
			field: 'categories_id',
		
				
			
		},

	}, {
		timestamps: false
	});
	return itemsCategories;
  };
  