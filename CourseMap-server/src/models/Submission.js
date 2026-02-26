const { DataTypes } = require("sequelize");

const sequelize = require("../config/db");

const Submission = sequelize.define("Submission", {

    id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },

    fileUrl: {
        type: DataTypes.STRING,
        allowNull: false
    }

});

module.exports = Submission;