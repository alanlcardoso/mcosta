$(function() {
	
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	
	var modal = $('#modalAdicionarServico');
	var botaoSalvar = modal.find('.js-modal-adicionar-servico');

	var form = modal.find('form');
	form.on('submit', function(event) { event.preventDefault()});
	var url = form.attr('action');
	var inputServico = $('#servico');
	var containerMensagemErro = $('.js-modal-adicionar-servico-mensagem');
	
	modal.on('shown.bs.modal', onModalShow);
	modal.on('hide.bs.modal', onModalClose);
	botaoSalvar.on('click', onBotaoSalvarClick);
	
	$(document).on('click','#removeLinhaSpan', function(){
		var servicoRecuperado = $(this).parent().siblings().text();
		$.ajax({
			url : contextPath + "/administracao/cliente/removeTelefone",
			beforeSend: function (xhr) { xhr.setRequestHeader(header,token);},
			method: 'POST',
			contentType: 'application/json',
			data: servicoRecuperado,
			success: onSucessoRemover($(this))
		});		
	});
	
	function onModalShow() {
		containerMensagemErro.addClass('hidden');
		form.find('.form-group').removeClass('has-error');
		inputServico.focus();
	}
	
	function onModalClose() {
		inputServiço.val('');
	}
	
	function onBotaoSalvarClick() {
		var servicoRecuperado = inputServiço.val().trim();
		$.ajax({
			url: url,
			beforeSend: function (xhr) { xhr.setRequestHeader(header,token);},
			method: 'POST',
			contentType: 'application/json',
			data: servicoRecuperado,
			error: onErrorSalvar,
			success: onSucessoSalvar
		});
	}
	
	function onErrorSalvar(obj) {
		var mensagemErro = obj.responseText;
		containerMensagemErro.removeClass('hidden');
		containerMensagemErro.html('<span>' + mensagemErro + '</span>');
		form.find('.form-group').addClass('has-error');
	}
	
	function onSucessoSalvar(servico) {
		var tabelaServico = $('#servicos');
		tabelaServico.append("<tr><td><span id='removeLinhaSpan' rel='tooltip' data-placement='top' title='Remover Serviço' class='fa fa-trash text-danger'></span></td><td class='center middle'>" + Serviço + "</td></tr>");		
		modal.modal('hide');
	}
	
	function onSucessoRemover(obj) {
		obj.parent().parent().remove();
	}
	
});