$(document).ready(function () {
	moment.locale('pt-BR');
    var table = $('#table-autorizacao').DataTable({
    	language: {
            "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Portuguese-Brasil.json"
        },
    	searching: false,
    	order: [[ 1, "asc" ]],
    	lengthMenu: [5, 10],
        processing: true,
        serverSide: true,
        responsive: true,
        ajax: {
            url: '/autorizacao/datatables/server',
            data: 'data'
        },
        columns: [
        	{data: 'refeicao', render:
                function( refeicao ) {
                    return moment(refeicao.data).format('LL');
                }
            },
            {data : 'alunos',
                render : function(alunos) {
    				var aux = new Array();
    				$.each(alunos, function( index, value ){
    					  var nome = " ";
    					  nome = nome.concat(value.nome);
    					 					  
    					  aux.push(nome);
    				});
    				return aux;
    			}, orderable : false,
            },
            {data : 'alunos',
                render : function(alunos) {
    				var aux;
    				$.each(alunos, function( index, value ){
    					  curso = value.curso.nome;
    				});
    				return curso;
    			}, orderable : false,
            },
            {data : 'alunos',
                render : function(alunos) {
    				var aux = new Array();
    				$.each(alunos, function( index, value ){
    					  var turma = " ";
    					  turma = turma.concat(value.turma.nome);
    					 					  
    					  aux.push(turma);
    				});
    				return aux;
    			}, orderable : false,
            },
            {orderable: false,
             data: 'id',
                "render": function(id) {
                    return '<a class="btn btn-danger btn-sm btn-block" href="/autorizacao/excluir/'+ 
                    	id +'" role="button" data-toggle="modal" data-target="#confirm-modal"><i class="fas fa-times-circle"></i></a>';
                }               
            }
        ]
    });
});    
