var contextPath = document.getElementById("contextPath").value;
var token = $("meta[name='_csrf']").attr('content');
var header = $("meta[name='_csrf_header']").attr("content");
var containerMensagemErro = $('.js-mensagem-erro');
var button = {};
var modal = {};
var form = {};

$('#confirmarRemocao').on(
		'show.bs.modal',
		function(event) {
			button = $(event.relatedTarget);
			modal = $(this);
			modal.on('shown.bs.modal', onModalShow);
			form = modal.find('form');
			var mensagem = button.data('mensagem');
			var descricao = button.data('descricao');
			if (descricao === undefined) {
				modal.find('.modal-body span').html(mensagem);
			} else {
				modal.find('.modal-body span').html(
						mensagem + ' <strong>' + descricao + '</strong>?');
			}

		});

$('#confirmarSim').click(function(e) {
	var parametro = undefined;
	var entidadeId = button.data('id');
	parametro = entidadeId;
	var url = contextPath + button.data('url');
	$.ajax({
		url : url,
		beforeSend : function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		method : 'POST',
		contentType : 'application/json',
		data : parametro + "",
		success : onSucessoExclusao
	});
});

function onSucessoExclusao(obj) {
	modal.modal('hide');
	location.reload();
};

function onModalShow() {
	containerMensagemErro.addClass('hidden');
	form.find('.form-group').removeClass('has-error');
	form.find('#confirmarSim').removeClass('hidden');
	form.find('#confirmarNao').html('Não');
	var requisicaoHtml = button.data('requisicaohtml');
	if (requisicaoHtml === true) {
		$('#confirmarSimHtml').removeClass('hidden');
		$('#confirmarSim').addClass('hidden');
		form.attr('action', (contextPath + button.data('url') + button
				.data('id')));
	}
};