

function removeBox(url){
    test("remover registro", function() {
        swal({
        title: "Tem certeza que quer remover?",
        text: "Você não poderá recuperar um registro removido do banco de dados depois!",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Sim, remover!",
        cancelButtonText: "Não, cancelar!",
        closeOnConfirm: false,
        closeOnCancel: false
    },
    function(isConfirm){
      if (isConfirm) {
          swal("Apagado!", "Você será redirecionado e o registro apagado do sistema.", "success");
                setTimeout(function () {
             window.location.href = url; 
          }, 2000); 
      } else {
        swal("Cancelado", "Seu registro está seguro :)", "error");
      }
    });
    });
}

function redirect(url) {
    setTimeout(function () {
     window.location.href = url; 
  }, 2000); 
}




