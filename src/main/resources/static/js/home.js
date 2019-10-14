
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
            url: '/autorizacao/home/datatables/server',
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
                	var nome = "";
    				$.each(alunos, function( index, value ){
    					  nome = nome.concat(value.nome);
    					  nome = nome.concat("<br>");
    				});
    				return nome;
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
                	var turma = "";
    				$.each(alunos, function( index, value ){
    					  turma = turma.concat(value.turma.nome);
    					  turma = turma.concat("<br>");
    				});
    				return turma;
    			}, orderable : false,
            },
         ]
    });
});    

