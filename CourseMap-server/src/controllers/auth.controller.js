const bcrypt = require("bcrypt");
const User = require("../models/User");

let currentSessionUser = null;


exports.register = async (req, res) => {

    try {

        const { email, password, role } = req.body;

        const userExist = await User.findOne({
            where: { email }
        });

        if (userExist) {

            return res.status(401).json({
                error: "User already exist"
            });

        }

        const hashedPassword = await bcrypt.hash(password, 10);

        const user = await User.create({
            email,
            password: hashedPassword,
            role
        });

        res.json({
            message: "User registered successfully",
            user: {
                id: user.id,
                email: user.email,
                role: user.role
            }
        });

    }
    catch (err) {

        res.status(500).json({
            error: err.message
        });

    }

};



exports.login = async (req, res) => {

    try {

        const { email, password } = req.body;

        const user = await User.findOne({
            where: { email }
        });

        if (!user) {

            return res.status(401).json({
                error: "User not found"
            });

        }

        const validPassword = await bcrypt.compare(password, user.password);

        if (!validPassword) {

            return res.status(401).json({
                error: "Invalid password"
            });

        }

        currentSessionUser = user;

        res.json({
            message: "Login successful",
            user: {
                id: user.id,
                email: user.email,
                role: user.role
            }
        });

    }
    catch (err) {

        res.status(500).json({
            error: err.message
        });

    }

};



exports.getCurrentUser = () => {
    console.log(currentSessionUser);
    
    return currentSessionUser;

};



exports.logout = (req, res) => {

    currentSessionUser = null;

    res.json({
        message: "Logged out successfully"
    });

};