function cargarModuloUsuarios()
{
    $.ajax
            ({
                url: "vista/usuario.html",
                context: document.body
            })
            .done(function (data)
            {
                $("#divContenidoC").html(data);
               
            });
            
            

}