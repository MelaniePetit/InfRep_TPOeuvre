$(document).ready(function() {
    $('#contact_form').bootstrapValidator({
        // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            txtnom: {
                validators: {
                    stringLength: {
                        min: 2,
                    },
                    notEmpty: {
                        message: 'Merci de renseigner votre nom'
                    }
                }
            },
            txtprenom: {
                validators: {
                    stringLength: {
                        min: 2,
                    },
                    notEmpty: {
                        message: 'Merci de renseigner votre prenom'
                    }
                }
            },
            txtville: {
                validators: {
                    stringLength: {
                        min: 4,
                    },
                    notEmpty: {
                        message: 'Merci de renseigner votre lieu de residence'
                    }
                }
            }
        }
    })
});

