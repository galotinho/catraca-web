
$(document).ready(function() {
    moment.locale('pt-BR');
    var table = $('#table-cardapio').DataTable({
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
            url : '/refeicao/home/datatables/server',
            data : 'data'
        },
        columns : [
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
            }
        ]
    });
});
