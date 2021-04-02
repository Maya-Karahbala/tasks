module.exports = (sequelize, DataTypes) => {
    const Items = sequelize.define("Items", {
      id: {
              type: DataTypes.INTEGER(11),
              primaryKey: true,
              autoIncrement: true,
          },
 
      image: {
        type: DataTypes.STRING,
      
      },
      gallery: {
        type: DataTypes.STRING,
   
      },
      text_editor: {
        type: DataTypes.STRING,
   
      }
    }, {
          timestamps: false
      });
    return Items;
  };
  