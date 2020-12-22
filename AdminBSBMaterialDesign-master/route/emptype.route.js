const express = require('express');
var router = express.Router();
const EmpTypeController = require('../controllers/emptype.controller');

//get all emptype
router.get('/', EmpTypeController.getAllEmpType);

//insert
router.post('/insert', EmpTypeController.insertOneEmpType);

//update
router.post('/update', EmpTypeController.updateOneEmpType);

//delete
router.get('/delete', EmpTypeController.deleteOneEmpType);

module.exports = router;