const EmpType = require('../models/emptype.model');

module.exports.getAllEmpType = (req, res) => {
    EmpType.find((err, data) => {
        if (!err) {
            res.render('emptype', {
                empTypeList: data
            })
        } else {
            res.send("Get all error: " + err)
        }
    })
}

module.exports.insertOneEmpType = (req, res) => {

    const empType = new EmpType({
        typeName: req.body.typeName,
        description: req.body.description
    })

    try {
        empType.save((err, data) => {
            if (!err) {
                res.redirect('http://localhost:8888/emptype');
            } else {
                res.send("Get all error: " + err)
            }
        })
    } catch (error) {
        console.log('Catch error: ' + error);
    }

}

module.exports.deleteOneEmpType = async (req, res) => {
    EmpType.findByIdAndRemove(req.query.id, (err, doc) => {
        if (!err) {
            res.redirect('/emptype');
        }
        else { console.log('Error in employee delete :' + err); }
    });
}

module.exports.updateOneEmpType = async (req, res) => {

    EmpType.findByIdAndUpdate(req.body.id,
        { typeName: req.body.typeName, description: req.body.description },
        (err, doc) => {

        if ( !err ) {
            res.redirect('/emptype');
        } else {
            console.log('Error: ' + err);
        }

    } )

}
