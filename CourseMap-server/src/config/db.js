const { Sequelize } = require("sequelize");

const sequelize = new Sequelize(
    "postgresql://neondb_owner:npg_0vZjBnHTeOG7@ep-dark-heart-aif10mmq-pooler.c-4.us-east-1.aws.neon.tech/neondb?sslmode=verify-full&channel_binding=require",
    {
        dialect: "postgres",
        logging: false,
        dialectOptions: {
            ssl: {
                require: true,
                rejectUnauthorized: false
            }
        }
    }
);

module.exports = sequelize;