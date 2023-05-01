console.log("V25 ")
var blocks = [];

var coursesDropdown = document.getElementById("coursesDropdown");

function populateCoursesDropdown(courses) {
    console.log(courses)
    var uniqueCourses = [...new Set(courses)];

  for (var i = -1; i < uniqueCourses.length; i++) {
    var option = document.createElement("option");
    option.text = uniqueCourses[i];
    coursesDropdown.add(option);
  }
}

function selectCourse(blocks) {
   selectedCourse = coursesDropdown.value;

   selectedBlocks = blocks.filter((block) => block.course === selectedCourse);
    drawCalendar(selectedBlocks);
   // coursesDropdown.classList.add("hide");
};

coursesDropdown.addEventListener("change", function() {
   selectCourse(blocks);
});


fetch('/api/blocks')
  .then(response => response.json())
  .then(data => {

  var courses = new Set();
    for (var i = 0; i < data.length; i++) {
      courses.add(data[i].course);
    }
    populateCoursesDropdown(courses)
    blocks = data
});

function drawCalendar(events) {
    // Inicializar o FullCalendar
    $('#calendar').fullCalendar('destroy');
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
    events: events, // Passar a lista de "blocks" como eventos do FullCalendar

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
}