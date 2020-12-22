const mongoose = require("mongoose");
const express = require("express");
const path = require("path");
const Handlebars = require("handlebars");
const exphbs = require("express-handlebars");
const bodyparser = require("body-parser");
const {
  allowInsecurePrototypeAccess,
} = require("@handlebars/allow-prototype-access");
const Emptype = require("./models/emptype.model");
const Employee = require("./models/employee.model");

var app = express();

app.use(
  bodyparser.urlencoded({
    extended: true,
  })
);

app.use(bodyparser.json());
app.use(express.static("public"));
app.set("views", path.join(__dirname, "/views/"));
app.set("view engine", "hbs");

app.engine(
  "hbs",
  exphbs({
    extname: "hbs",
    defaultLayout: "mainLayout",
    layoutsDir: __dirname + "/views/layouts/",
    handlebars: allowInsecurePrototypeAccess(Handlebars),
    editEmptype: () => {
      return new Handlebars.SafeString("");
    },
  })
);

app.use("/home", (req, res) => {
  res.render("home");
});

const EmpTypeRoute = require("./route/emptype.route");
app.use("/emptype", EmpTypeRoute);
app.get(
  "/empTypeAndroid",
  (getAllType = async (req, res) => {
    try {
      await Emptype.find((err, data) => {
        if (!err) {
          res.send(data);
        } else {
          res.send(err);
        }
      });
    } catch (error) {
      res.send("Catch error: " + error);
    }
  })
);

const EmployeeRoute = require("./route/employee.route");
app.use("/employee", EmployeeRoute);
app.get(
  "/employeeAndroid",
  (getAllEmp = async (req, res) => {
    try {
      await Employee.find()
        .populate({ path: "position" })
        .exec((err, data) => {
          if (!err) {
            res.send(data);
          } else {
            res.send(err);
          }
        });
    } catch (error) {
      res.send("Catch error: " + error);
    }
  })
);

app.listen(8888, () => {
  console.log("Express server started at port : 8888");
  mongoose.connect(
    "mongodb://localhost:27017/EmployeeDB",
    {
      useNewUrlParser: true,
      useUnifiedTopology: true,
    },
    (err) => {
      if (!err) {
        console.log("MongoDB Connection Succeeded.");
      } else {
        console.log("Error in DB connection : " + err);
      }
    }
  );
});
