const express = require("express");

const router = express.Router();

const controller = require("../controllers/course.controllers");

router.get("/", controller.getAllCourses);

router.post("/", controller.createCourse);

module.exports = router;