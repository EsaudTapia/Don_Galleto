var proveedores = [];





function confirmarNuevoProveedor() {

    var razonSocial = $("#txtRazonSocial").val();
    var rfc = $("#txtRFC").val();
    var nombre = $("#txtNombre").val();
    var aPaterno = $("#txtApellidoP").val();
    var aMaterno = $("#txtApellidoM").val();
    var tel1 = $("#txtTel1").val();
    var domicilio = $("#txtDomicilio").val();

    

    $.ajax({
        type: "POST",
        url: "rest/proveedor/save",
        async: true,

        data:
                {
                    nombre: nombre,
                    apellidoPaterno: aPaterno,
                    apellidoMaterno: aMaterno,                   
                    domicilio: domicilio,
                    tel1: tel1,               
                    razonSocial: razonSocial,
                    rfc: rfc
                  
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
 //cargarTablaProveedores();
        }
           

    });

//    $("#txtRazonSocial").val(null);
//    $("#txtRFC").val(null);
//    $("#txtNombre").val(null);
//    $("#txtApellidoP").val(null);
//    $("#txtApellidoM").val(null);
//    $("#txtTel1").val(null);
//    $("#txtDomicilio").val(null);
    
    

}


function cargarTablaProveedores() {
  
    var contenido = '';
    $.ajax
            ({
                type: "GET",
                url: "rest/proveedor/getAll",              

            }).done(function (data) {
      if (data.exception != null) {
            Swal.fire("La operacion no pudo ser completada",
                    data.exception,
                    "error");
        } else if (data.length < 1) {
            contenido = '<tr>' +
                    '<td colspan="12" class="text-center">' +
                    '<img alt="" src="style/media/img/dogsad.png" style="height: 120px;"/><br>' +
                    '<span class="text-danger font-weight-bold">' +
                    'Por el momento no tienes proveedores en tu catálogo.' +
                    '</span>' +
                    '</td>' +
                    '</tr>';
            $("#tbProveedor").html(contenido);
            return;
        } else
        {
            proveedores = data;
            for (var i = 0; i < proveedores.length; i++) {
                if (proveedores[i].persona.estatus === 1)
                {
                    contenido +=
                            '<tr>' +
                            '<td>' + proveedores[i].persona.nombre + " "+proveedores[i].persona.apellidoPaterno+" "+ proveedores[i].persona.apellidoMaterno+'</td>' +
                            '<td>' + proveedores[i].razonSocial + '</td>' +
                            '<td>' + proveedores[i].rfc + '</td>' +                                                                            
                            '<td>' + proveedores[i].persona.tel1 + '</td>' +
                            '<td>' + proveedores[i].persona.domicilio + '</td>' +
                            '<td>' + '<i class="fal fa-check-square"></i>' + '</td>' +
                            '<td>' +
                            '<div class="btn-group">' +
                            '<button class="btn btn-sm btn-warning" onclick="editProveedor(' + i + ');" data-toggle="modal" data-target="#exampleModal"><i class="fal fa-edit"></i> Editar</button>' +
                            '<button class="btn btn-sm btn-danger"  onclick="deleteProveedor(' + i + ');"><i class="fal fa-trash-alt"></i> Eliminar</button>' +
                            '</div>' +
                            '</td>' +
                            '</tr>';
                } else {
                    contenido +=
                            '<tr>' +
                             '<td>' + proveedores[i].persona.nombre + " "+proveedores[i].persona.apellidoPaterno+" "+ proveedores[i].persona.apellidoMaterno+'</td>' +
                            '<td>' + proveedores[i].razonSocial + '</td>' +
                            '<td>' + proveedores[i].rfc + '</td>' +                                                                            
                            '<td>' + proveedores[i].persona.tel1 + '</td>' +
                            '<td>' + proveedores[i].persona.domicilio + '</td>' +
                            '<td>' + '<i class="fal fa-square"></i>' + '</td>' +
                            '<td>' +
                            '<div class="btn-group">' +
                            '<button class="btn btn-sm btn-warning" onclick="editProveedor(' + i + ');" data-toggle="modal" data-target="#exampleModal"><i class="fal fa-edit"></i> Editar</button>' +
                            '<button class="btn btn-sm btn-success" onclick="activarProveedor(' + i + ');"><i class="fal fa-plus"></i> Activar</button>' +
                            '</div>' +
                            '</td>' +
                            '</tr>';
                }
            }
            $("#tbProveedor").html(contenido);
        }
    });

}

function editProveedor(pos)
{
    
    var idpersona= proveedores[pos].persona.id;
    var idpersona= proveedores[pos].id;
    $("#txtRazonSocial").val(proveedores[pos].razonSocial);
    $("#txtRFC").val(proveedores[pos].rfc);
    $("#txtNombre").val(proveedores[pos].persona.nombre);
    $("#txtApellidoP").val(proveedores[pos].persona.apellidoPaterno);
    $("#txtApellidoM").val(proveedores[pos].persona.apellidoMaterno);
    $("#txtTel1").val(proveedores[pos].persona.tel1);
    $("#txtDomicilio").val(proveedores[pos].persona.domicilio);
   $("#btnGuardar").attr("onclick","guardarEditProveedor()");
   
}

function guardarEditProveedor()
{
    
    localStorage.setItem("nombre", nombre);
    var idProveedor = $("#txtCodigoInterno").val();
    var razonSocial = $("#txtRazonSocial").val();
    var rfc = $("#txtRFC").val();
    var nombre = $("#txtNombre").val();
    var aPaterno = $("#txtApellidoP").val();
    var aMaterno = $("#txtApellidoM").val();
    var tel1 = $("#txtTel1").val();
    var domicilio = $("#txtDomicilio").val();


    $.ajax
            ({
                type: "POST",
                url: "rest/proveedor/save",
                async: true,
                data:
                        {
                            idProveedor: idProveedor,
                            nombre: nombre,
                            apellidoPaterno: aPaterno,
                            apellidoMaterno: aMaterno,                  
                            domicilio: domicilio,                          
                            tel1: tel1,                          
                            razonSocial: razonSocial,
                            rfc: rfc
                          
                        }
            }).done(function (data)
    {
        if (data == null) {
             Swal.fire({
                title: 'sesion caducada',
                text: "se ha cerrado la sesion!",
                icon: 'warning',
                confirmButtonColor: '#3085d6',
                confirmButtonText: 'ir,a iniciar sesion !'
            }).then((result) => {
                if (result.isConfirmed) {
                    window.location.href = "login.html";
                }
            })

        } else if (data.exception != null)
        {
            Swal.fire("Error en el registro",
                    data.exception,
                    "error");
        } else
        {
            Swal.fire("Registro exitoso",
                    "Proveedor actualizado correctamente",
                    "success");
            mostrarTablaProveedores();
        }
    });
}