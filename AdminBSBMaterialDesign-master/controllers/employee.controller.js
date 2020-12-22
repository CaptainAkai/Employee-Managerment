const Employee = require('../models/employee.model.js');
const Emptype = require('../models/emptype.model');
const multer = require('multer');

module.exports.getAllEmpployee = async (req, res) => {
    const emptypes = await Emptype.find({});
    await Employee.find({}).populate('position').exec((err, data) => {
        if (!err) {
            console.log(data);
            // res.send(data);
            res.render('employee', {
                employeeList: data,
                empTypeList: emptypes
            })
        } else {
            res.send("Get all error: " + err)
        }
    })
}

module.exports.insertOneEmployee = async (req, res) => {
    var storage = multer.diskStorage({
        destination: function (req, file, cb) {
            if (file.mimetype === "image/jpg" ||
                file.mimetype === "image/jpeg" ||
                file.mimetype === "image/png") {
                cb(null, './public/uploads');
            } else {
                cb(new Error("not image"), null);
            }
        },
        filename: function (req, file, cb) {
            //hàm đổi đuôi
            //cb(null,file.originalname);
            cb(null, Date.now() + '.jpg');
        }
    });

    var upload = multer({ storage: storage });
    const uploads = await upload.single('avatar');
    uploads(req, res, async (err) => {

        if (!err) {

            const path = req.file.destination;
            const np = path.substring(8) + "/" + req.file.filename;

            const employee = new Employee({
                avatar: np,
                fullName: req.body.fullName,
                email: req.body.email,
                mobile: req.body.mobile,
                city: req.body.city,
                position: req.body.position
            })

            try {
                employee.save((err, data) => {
                    if (!err) {
                        res.redirect('/employee');
                    } else {
                        res.send("Get all error: " + err)
                    }
                })
            } catch (error) {
                console.log('Catch error: ' + error);
            }
        } else {
            res.send('all err: ' + err);
        }

    });

}

module.exports.deleteOneEmployee = async (req, res) => {
    Employee.findByIdAndRemove(req.query.id, (err, doc) => {
        if (!err) {
            res.redirect('/employee');
        }
        else { console.log('Error in employee delete :' + err); }
    });
}

module.exports.updateOneEmployee = async (req, res) => {

    Employee.findByIdAndUpdate(req.body.id,
        {
            avatar: req.body.avatar,
            fullName: req.body.fullName,
            email: req.body.email,
            mobile: req.body.mobile,
            city: req.body.city,
            position: req.body.position
        },
        (err, doc) => {

            if (!err) {
                res.redirect('/employee');
            } else {
                console.log('Error: ' + err);
            }

        })

}