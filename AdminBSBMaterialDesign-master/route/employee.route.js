const express = require('express');
var router = express.Router();
const EmployeeController = require('../controllers/employee.controller');

//get all emptype
router.get('/', EmployeeController.getAllEmpployee);

//insert
router.post('/insert', EmployeeController.insertOneEmployee);

//update
router.post('/update',EmployeeController.updateOneEmployee);

//delete
router.get('/delete', EmployeeController.deleteOneEmployee);

module.exports = router;