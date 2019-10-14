
$(document).ready(function() {
    moment.locale('pt-BR');
    var table = $('#table-refeicao').DataTable({
    	language: {
            "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Portuguese-Brasil.json"
        },
        searching : false,
        lengthMenu : [ 5, 10 ],
        processing : true,
        serverSide : true,
        responsive : true,
        order: [1, 'desc'],
        ajax : {
            url : '/refeicao/datatables/server',
            data : 'data'
        },
        columns : [
            {data : 'id'},
            {data: 'data', render:
                function( data ) {
                    return moment(data).format('LL');
                }
            },
            {data : 'alimentos',
            render : function(alimentos) {
				var aux = new Array();
				$.each(alimentos, function( index, value ){
					  var alimento = " ";
					  alimento = alimento.concat(value.nome);
					  aux.push(alimento);
				});
				return aux;
			}, orderable : false,
            },
            {orderable : false,	data : 'id', "render" : function(id) {

                    return '<a class="btn btn-success btn-sm btn-block" href="/refeicao/editar/'
                            + id + '" role="button"><i class="fas fa-edit"></i></a>';
                }
            },
            {orderable : false,	data : 'id', "render" : function(id) {
                    return '<a class="btn btn-danger btn-sm btn-block" href="/refeicao/excluir/'
                    + id +'" role="button" data-toggle="modal" data-target="#confirm-modal"><i class="fas fa-times-circle"></i></a>';
                }
            }
        ]
    });
});
