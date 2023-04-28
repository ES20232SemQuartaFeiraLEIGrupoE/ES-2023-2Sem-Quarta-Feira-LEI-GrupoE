
function createBlock(title, date, startHour, endHour, room, course, turno, prof, numeroInsc, sizeRoom) {
var start = date + 'T' + startHour + ':00';
var end = date + 'T' + endHour + ':00';

return {
  title: title,
  start: start,
  end: end,
  room: room,
  course: course,
  turno: turno,
  prof: prof,
  numeroInsc: numeroInsc,
  sizeRoom: sizeRoom
};
}

var blocks = [
        createBlock('Disciplina 1', '2023-05-01', '08:30', '09:00', 'D104', 'LEI', 'manha', 'Luis', '5', '10'),
        createBlock('Disciplina 2', '2023-05-01', '09:30', '10:00', 'D104', 'LEI', 'manha', 'Luis', '5', '10'),
        createBlock('Disciplina 3', '2023-05-02', '10:00', '11:00', 'D105', 'LCC', 'tarde', 'Maria', '7', '20'),
        createBlock('Disciplina 4', '2023-05-02', '11:30', '12:30', 'D105', 'LCC', 'tarde', 'Maria', '7', '20'),
        createBlock('Disciplina 5', '2023-05-03', '14:00', '16:00', 'D203', 'MEI', 'noite', 'Joana', '15', '30'),
        createBlock('Disciplina 6', '2023-05-04', '09:00', '10:00', 'D205', 'LGI', 'manha', 'Ricardo', '12', '15'),
        createBlock('Disciplina 7', '2023-05-05', '16:00', '17:00', 'D203', 'MIEC', 'tarde', 'Ana', '6', '25'),
        createBlock('Disciplina 8', '2023-05-06', '11:00', '12:00', 'D202', 'MIEC', 'manha', 'Pedro', '9', '15'),
        createBlock('Disciplina 9', '2023-05-07', '15:00', '16:30', 'D105', 'LCC', 'tarde', 'Maria', '7', '20'),
        createBlock('Disciplina 10', '2023-05-08', '18:00', '20:00', 'D201', 'LEI', 'noite', 'Rui', '20', '40')
        ];


// Inicializar o FullCalendar
$('#calendar').fullCalendar({
header: {
left: 'prev,next today',
center: 'title',
right: 'month,agendaWeek,agendaDay'
},
height: 'auto', // Ajustar automaticamente a altura ao número de eventos
contentHeight: 'auto', // Ajustar a altura do conteúdo do calendário para que caiba todos os eventos
defaultView: 'agendaWeek',
minTime: '08:00:00', // Hora de início do horário
maxTime: '20:30:00', // Hora de término do horário
slotDuration: '00:30:00', // Duração de cada slot de tempo (30 minutos)
slotLabelFormat: 'h:mm A', // Formato do rótulo de tempo
allDaySlot: false, // Não mostrar o slot de tempo para o dia inteiro
events: blocks, // Passar a lista de "blocks" como eventos do FullCalendar

eventRender: function(event, element) {
// Montar o título do evento com o nome da disciplina e a sala
var title = event.title + ' - Sala ' + event.room;
// Adicionar um botão para ver mais detalhes
var detailsButton = $('<button>').addClass('btn btn-sm btn-info').text('Ver detalhes');
element.find('.fc-title').append(detailsButton);
// Definir o título no elemento do evento
element.find('.fc-title').text(title);
},
eventClick: function(event) {
// Verificar se o modal já está aberto
if ($('#event-details-modal').length > 0) {
$('#event-details-modal').remove();
}

// Criar o modal com as informações do evento
var modal = $('<div>').addClass('modal fade').attr('id', 'event-details-modal');
var modalDialog = $('<div>').addClass('modal-dialog');
var modalContent = $('<div>').addClass('modal-content');
var modalHeader = $('<div>').addClass('modal-header').html('<h5 class="modal-title">' + event.title);
var modalBody = $('<div>').addClass('modal-body').html('Professor: ' + event.prof + '<br>Número de inscrições: ' + event.numeroInsc + '<br>Tamanho da sala: ' + event.sizeRoom);
modalContent.append(modalHeader, modalBody);
modalDialog.append(modalContent);
modal.append(modalDialog);

// Adicionar o modal ao corpo da página e exibi-lo
$('body').append(modal);
modal.modal('show');
}
});