var usuario = [];





function confirmarNuevoUsuario() {

    var nombre = $("#txtNombre").val();
    var apellidoP = $("#txtApellidoP").val();
    var apellidoM = $("#txtApellidoM").val();
    var nombreCompleto =nombre+" "+apellidoP+" "+apellidoM;
    var userName = $("#txtUserName").val();
    var fechNac = $("#txtFechNac").val();
    var correo = $("#txtCorreo").val();

    

    $.ajax({
        type: "POST",
        url: "rest/usuario/save",
        async: true,

        data:
                {
                    nombre: nombreCompleto,
                    userName: userName,
                    fechNac: fechNac,                   
                    correo: correo
                                     
                }
    }).done(function (data) {

        if (data.exception != null)
        {
            Swal.fire("Error en el registro",
                    data.exception,
                    "error");
        } else
        {
            Swal.fire("Registro exitoso",
                    "Proveedor registrada correctamente",
                    "success");
//            mostrarTablaProveedores();
 cargarTablaProveedores();
        }
           

    });

    $("#txtRazonSocial").val(null);
    $("#txtRFC").val(null);
    $("#txtNombre").val(null);
    $("#txtApellidoP").val(null);
    $("#txtApellidoM").val(null);
    $("#txtTel1").val(null);
    $("#txtDomicilio").val(null);
    
    

}
