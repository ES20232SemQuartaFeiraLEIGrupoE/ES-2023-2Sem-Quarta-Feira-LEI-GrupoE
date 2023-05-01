console.log("V36 ")
var blocks = [];

const coursesDropdown = document.getElementById("coursesDropdown");
const btnLoadFile = document.querySelector(".btn-load-file");
const fileInput = document.getElementById('file-input');

btnLoadFile.addEventListener('click', function() {
  const file = fileInput.files[0];
  const formData = new FormData();
  formData.append('file', file);
  blocks = [];
  fetch('/api/blocks', {
    method: 'POST',
    body: formData
  })
  .then(response => response.json())
  .then(data => {
    console.log(data)
    var courses = new Set();
    for (var i = 0; i < data.length; i++) {
      courses.add(data[i]["Curso"]);
    }
    populateCoursesDropdown(courses)
    blocks = data
    populateSubjectChecklist(blocks)
    drawCalendar(blocks);
  });
});

function populateCoursesDropdown(courses) {
    console.log(courses)
    var uniqueCourses = [...new Set(courses)];
    var option = document.createElement("option");
    option.text = "All";
    coursesDropdown.add(option);

  for (var i = 0; i < uniqueCourses.length; i++) {
    var option = document.createElement("option");
    option.text = uniqueCourses[i];
    coursesDropdown.add(option);
  }
}

function selectCourse(blocks) {
   selectedCourse = coursesDropdown.value;

    if("All" == selectedCourse){
        populateSubjectChecklist(blocks)
        drawCalendar(blocks);
    }else{
       selectedBlocks = blocks.filter((block) => block["Curso"] === selectedCourse);
       populateSubjectChecklist(selectedBlocks)
       drawCalendar(selectedBlocks);
    }
};

coursesDropdown.addEventListener("change", function() {
   selectCourse(blocks);
});

// Tarefe 22
 const subjectSelector = document.getElementById("subjectSelector");

function populateSubjectChecklist(blocks) {
    console.log(blocks)
    console.log(blocks[0]["Unidade Curricular"])
  subjectSelector.innerHTML = ""; // clear existing checkboxes
  const subjects = new Set(blocks.map(block => block["Unidade Curricular"])); // get unique subjects
  console.log(subjects)
  for (const subject of subjects) {
    const li = document.createElement("li");
    const label = document.createElement("label");
    const checkbox = document.createElement("input");
    checkbox.type = "checkbox";
    checkbox.name = subject;
    checkbox.value = subject;
    checkbox.checked = true;
    label.appendChild(checkbox);
    label.appendChild(document.createTextNode(subject));
    li.appendChild(label);
    subjectSelector.appendChild(li);
  }
}

subjectSelector.addEventListener("change", () => {
    const selectedCheckboxes = Array.from(subjectSelector.querySelectorAll("input[type=checkbox]:checked"));
    const selectedSubjects = selectedCheckboxes.map(checkbox => checkbox.value);
    if (selectedSubjects.length === 0) {
      drawCalendar([]); // No checkboxes are selected, draw all blocks
    } else {
      const selectedBlocks = blocks.filter(block => selectedSubjects.includes(block["Unidade Curricular"]));
      drawCalendar(selectedBlocks); // Draw selected blocks only
    }
    });



function drawCalendar(events) {
    // Inicializar o
    currentView = $('#calendar').fullCalendar('getView').name;
    $('#calendar').fullCalendar('destroy');
    $('#calendar').fullCalendar({
    header: {
    left: 'prev,next today',
    center: 'title',
    right: 'month,agendaWeek,agendaDay'
    },
    height: 'auto', // Ajustar automaticamente a altura ao número de eventos
    contentHeight: 'auto', // Ajustar a altura do conteúdo do calendário para que caiba todos os eventos
    defaultView: currentView ,
    minTime: '08:00:00', // Hora de início do horário
    maxTime: '20:30:00', // Hora de término do horário
    slotDuration: '00:30:00', // Duração de cada slot de tempo (30 minutos)
    slotLabelFormat: 'h:mm A', // Formato do rótulo de tempo
    allDaySlot: false, // Não mostrar o slot de tempo para o dia inteiro
    events: events, // Passar a lista de "blocks" como eventos do FullCalendar

    eventRender: function(event, element) {
    // Montar o título do evento com o nome da disciplina e a sala
    var title = event["Unidade Curricular"] + ' - Sala ' + event["Sala atribuída à aula"];
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
    var modalHeader = $('<div>').addClass('modal-header').html('<h5 class="modal-title">' + event["Unidade Curricular"]);
    var modalBody = $('<div>').addClass('modal-body').html('<br>Número de inscrições: ' + event["Inscritos no turno"] + '<br>Tamanho da sala: ' + event["Lotação da sala"] );
    modalContent.append(modalHeader, modalBody);
    modalDialog.append(modalContent);
    modal.append(modalDialog);

    // Adicionar o modal ao corpo da página e exibi-lo
    $('body').append(modal);
    modal.modal('show');
    }
    });
}