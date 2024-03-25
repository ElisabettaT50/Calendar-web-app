package calendar.example.calendar.service;

import calendar.example.calendar.entities.Calendar;
import calendar.example.calendar.entities.Event;
import calendar.example.calendar.repositories.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalendarService {
    @Autowired
    private CalendarRepository repository;
    @Autowired
    private EventService eventoService;
    /**
     * dato un Calendario in ingresso viene salvato
     * al db tramite la repository.
     * @param calendar
     * @return Evento
     */
    public Calendar addCalendar(Calendar calendar){
        //salviamo l'oggetto e poi lo ritorniamo
        return repository.save(calendar);
    }
    /**
     * richiede tutta la lista di Calendario presenti
     * sul db tramite la repository.
     * Successivamente ritorna la lista di Calendario presenti sul db.
     * @return List</>
     */
    public List<Calendar> getCalendars(){
        //ritorniamo tutta la lista degli oggetti
        return repository.findAll();
    }
    /**
     * dato un Long in ingresso che rappresenta l'id del Calendario
     * viene cercato il Calendario con il medesimo id tramite la repository,
     * prima di ritornare il Calendario viene controllato che
     * l'oggetto sia presente,
     * in caso contrario viene ritornato un oggetto Optional vuoto.
     * @param id
     * @return Optional</>
     */
    public Optional<Calendar> getCalendarById(Long id){
        //cerchiamo l'oggetto tramite id
        Optional<Calendar> calendarioOptional = repository.findById(id);
        //controlliamo che l'oggetto Optional sia presente
        if(calendarioOptional.isPresent()){
            //se presente ritorniamo l'oggetto Optional
            return calendarioOptional;
        }else {
            //se non presente ritorniamo un oggetto Optional vuoto
            return Optional.empty();
        }
    }
    /**
     * Dato un Long id in ingresso viene richiesto al db
     * un Calendario con il medesimo id,
     * se presente viene utilizzato il Calendario preso in
     * ingresso per modificare tutti i dati dell'evento
     * e infine viene ritornato l'oggetto Calendario modificato.
     * se invece non è presente viene ritornato un oggetto vuoto.
     * @param calendar
     * @param id
     * @return Optional</>
     */
    public Optional<Calendar> updateCalendar(Calendar calendar, Long id){
        //recuperiamo l'oggetto da modificare grazie all'id
        Optional<Calendar> calendarOptional = getCalendarById(id);
        //controlliamo se l'oggetto è presente
        if(calendarOptional.isPresent()){
            //modifichiamo tutti i parametri dell'oggetto
            calendarOptional.get().setName(calendar.getName());
            calendarOptional.get().setDescription(calendar.getDescription());
            calendarOptional.get().setColor(calendar.getColor());
            //salviamo l'oggetto aggiornato
            Calendar modifiedCalendar = repository.save(calendarOptional.get());
            //ritorniamo l'oggetto che è stato aggiornato
            return Optional.of(modifiedCalendar);
        }else {
            //se non presente ritorniamo un oggetto vuoto
            return Optional.empty();
        }
    }
    /**
     * dato un Long id in ingresso viene richiesto
     * al db un Calendario col medesimo id.
     * se presente viene cancellato il Calendario dal db
     * e infine viene ritornato il Calendario eliminato.
     * Se non presente viene ritornato un oggetto Optional vuoto.
     * @param id
     * @return Optional</>
     */
    public Optional<Calendar> deleteCalendarById(Long id){
        //recuperiamo l'oggetto da eliminare tramite l'id
        Optional<Calendar> calendarOptional = getCalendarById(id);
        //controlliamo che l'oggetto sia presente
        if(calendarOptional.isPresent()){
            //cancelliamo l'oggetto
            repository.delete(calendarOptional.get());
            //ritorniamo l'oggetto cancellato
            return calendarOptional;
        }else {
            //se non presente viene ritornato un oggetto vuoto
            return Optional.empty();
        }
    }

    /**
     * Dato un Long idCalendario in ingresso si recupera un Calendario con il mededimo id,
     * se presente viene utilizzato l' altro Long idEvento per recuperare un Evento con il medesimo id,
     * se presente viene controllato che L'Evento con lo stesso id non sia già presente nella lista di Evento del Calendario,
     * se non presente viene effettuato il collegamento dell'Evento al Calendario.
     * Successivamente viene salvato l'Evento modificato e poi si ritorna il Calendario.
     * se qualsiasi condizione non sia vera viene ritornato un oggetto Optional vuoto.
     * @param idCalendar
     * @param idEvent
     * @return Optional</>
     */
    public Optional<Calendar> addEventToCalendar(Long idCalendar,Long idEvent){
        //recuperiamo l'oggetto da eliminare tramite l'id
        Optional<Calendar> calendarOptional = getCalendarById(idCalendar);
        //controlliamo che l'oggetto sia presente
        if(calendarOptional.isPresent()){
            //se presente recuperiamo l'oggetto da aggiungere tramite l'id
            Optional<Event> eventOptional = eventoService.getEventById(idEvent);
            //controlliamo se l'oggetto da aggiungere sia presente
            if(eventOptional.isPresent()) {
                //se presente controlliamo se l'oggetto che vogliamo aggiungere non sia già presente nella lista
                if(!calendarOptional.get().getEventList().contains(eventOptional.get())) {
                    //se non presente effettuiamo il collegamento con l'oggetto da aggiungere e l'oggetto a cui vogliamo aggiungerlo
                    eventOptional.get().setCalendar(calendarOptional.get());
                    //salviamo l'oggetto da aggiungere
                    eventoService.addEvent(eventOptional.get());
                    //in fine ritorniamo il nostro oggetto
                    return calendarOptional;
                }else {
                    //se non presente torniamo un oggetto optional vuoto
                    return Optional.empty();
                }
            }else {
                //se non presente torniamo un oggetto optional vuoto
                return Optional.empty();
            }
        }else{
            //se non presente torniamo un oggetto optional vuoto
            return Optional.empty();
        }
    }
}