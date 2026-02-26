const { DataTypes } = require("sequelize");

const sequelize = require("../config/db");

const Course = sequelize.define("Course", {

    id: {
        type: DataTypes.STRING,
        primaryKey: true
    },

    name: {
        type: DataTypes.STRING,
        allowNull: false
    },

});

module.exports = Course;