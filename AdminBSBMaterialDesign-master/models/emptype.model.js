const mongoose = require('mongoose');

var empTypeSchema = new mongoose.Schema({
    typeName: {
        type: String,
        required: 'This field is required.'
    },
    description: {
        type: String
    }
});
module.exports = mongoose.model('Emptype', empTypeSchema);