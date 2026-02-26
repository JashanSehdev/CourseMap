const sequelize = require("../config/db");

const User = require("./User");
const Course = require("./Course");
const Assignment = require("./Assignment");
const Submission = require("./Submission");

// You can define relationships here later

module.exports = {
    sequelize,
    User,
    Course,
    Assignment,
    Submission
};