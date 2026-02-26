const db = require("./models");


const express = require("express");
const cors = require("cors");

const courseRoutes = require("./routes/courses.routes.js");
const authRoutes = require("./routes/auth.routes.js");

const app = express();

app.use(cors());
app.use(express.json());

//=======================================================
db.sequelize.authenticate()
.then(() => {
    console.log("Database connected successfully");
})
.catch(err => {
    console.error("Database connection failed:", err);
});

// synchronize or automatically create table

// uncomment this sync function in final production
// db.sequelize.sync()
// .then(() => {
//     console.log("Tables synced");
// });
//=========================================================


app.get("/", (req, res) => {

    res.send("CourseMap API is running");

});

app.use("/api/courses", courseRoutes);
app.use("/api/auth", authRoutes);

const PORT = process.env.PORT || 3000;

app.listen(PORT, () => {

    console.log(`Server running on port ${PORT}`);

});