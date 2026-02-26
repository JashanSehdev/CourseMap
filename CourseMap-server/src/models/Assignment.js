const { DataTypes } = require("sequelize");

const sequelize = require("../config/db");

const Assignment = sequelize.define("Assignment", {

    id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    
    title: {
        type: DataTypes.STRING,
        allowNull: false
    },

    description: {
        type: DataTypes.TEXT
    },

    dueDate: {
        type: DataTypes.DATE
    }

});

module.exports = Assignment;