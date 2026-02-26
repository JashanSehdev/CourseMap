const pool = require("../config/db");

exports.getAllCourses = async (req, res) => {

    try {

        const result = await pool.query("SELECT * FROM courses");

        res.json(result.rows);

    } catch (err) {

        res.status(500).json({ error: err.message });

    }

};

exports.createCourse = async (req, res) => {

    const { id, name } = req.body;

    try {

        await pool.query(
            "INSERT INTO courses (id, name) VALUES ($1, $2)",
            [id, name]
        );

        res.status(201).json({
            message: "Course created"
        });

    } catch (err) {

        res.status(500).json({ error: err.message });

    }

};