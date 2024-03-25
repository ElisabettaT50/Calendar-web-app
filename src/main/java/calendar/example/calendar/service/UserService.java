package calendar.example.calendar.service;

import calendar.example.calendar.entities.Calendar;
import calendar.example.calendar.entities.User;
import calendar.example.calendar.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private CalendarService calendarService;
    /**
     * dato un User in ingresso viene salvato
     * al db tramite la repository.
     * @param user
     * @return Evento
     */
    public User addUser(User user){
        //salviamo l'oggetto e poi lo ritorniamo
        return repository.save(user);
    }
    /**
     * richiede tutta la lista di User presenti
     * sul db tramite la repository.
     * Successivamente ritorna la lista di User presenti sul db.
     * @return List</>
     */
    public List<User> getAll(){
        //ritorniamo tutta la lista degli oggetti
        return repository.findAll();
    }
    /**
     * dato un Long in ingresso che rappresenta l'id dell'User
     * viene cercato l'User con il medesimo id tramite la repository,
     * prima di ritornare l'User viene controllato che
     * l'oggetto sia presente,
     * in caso contrario viene ritornato un oggetto Optional vuoto.
     * @param id
     * @return Optional</>
     */
    public Optional<User> getUserFromId(Long id){
        //cerchiamo e poi ritorniamo l'oggetto tramite id
        Optional<User> userOptional = repository.findById(id);
        //controlliamo che l'oggetto Optional sia presente
        if(userOptional.isPresent()){
            //se presente ritorniamo l'oggetto Optional
            return userOptional;
        }else {
            //se non presente ritorniamo un oggetto Optional vuoto
            return Optional.empty();
        }
    }
    /**
     * Dato un Long id in ingresso viene richiesto al db
     * un User con il medesimo id,
     * se presente viene utilizzato l' User preso in
     * ingresso per modificare tutti i dati dell'evento
     * e infine viene ritornato l'oggetto User modificato.
     * se invece non è presente viene ritornato un oggetto vuoto.
     * @param user
     * @param id
     * @return Optional</>
     */
    public Optional<User> updateUser(Long id,User user){
        //recuperiamo l'oggetto da modificare grazie all'id
        Optional<User> userOptional = getUserFromId(id);
        //controlliamo se l'oggetto è presente
        if(userOptional.isPresent()){
            //modifichiamo tutti i parametri dell'oggetto
            userOptional.get().setName(user.getName());
            userOptional.get().setSurname(user.getSurname());
            userOptional.get().setDateOfBirth(user.getDateOfBirth());
            //salviamo l'oggetto aggiornato
            User userUpdated = repository.save(userOptional.get());
            //ritorniamo l'oggetto che è stato aggiornato
            return Optional.of(userUpdated);
        }else {
            //se non presente ritorniamo un oggetto vuoto
            return Optional.empty();
        }
    }
    /**
     * dato un Long id in ingresso viene richiesto
     * al db un User col medesimo id.
     * se presente viene cancellato l'User dal db
     * e infine viene ritornato l'User eliminato.
     * Se non presente viene ritornato un oggetto Optional vuoto.
     * @param id
     * @return Optional</>
     */
    public Optional<User> deleteUserFromId(Long id){
        //recuperiamo l'oggetto da eliminare tramite l'id
        Optional<User> userOptional = getUserFromId(id);
        //controlliamo che l'oggetto sia presente
        if(userOptional.isPresent()){
            //cancelliamo l'oggetto
            repository.delete(userOptional.get());
            //ritorniamo l'oggetto cancellato
            return userOptional;
        }else {
            //se non presente viene ritornato un oggetto vuoto
            return Optional.empty();
        }
    }
    /**
     * Dato un Long idUser in ingresso si recupera uno User con il mededimo id,
     * se presente viene utilizzato l' altro Long idCalendario per recuperare un Calendario con il medesimo id,
     * se presente viene controllato che il Calendario con lo stesso id non sia già presente nella lista di Calendario dello User,
     * se non presente viene effettuato il collegamento del Calendario allo User.
     * Successivamente viene salvato il Calendario modificato e poi si ritorna l' User.
     * se qualsiasi condizione non sia vera viene ritornato un oggetto Optional vuoto.
     * @param idUser
     * @param idCalendar
     * @return Optional</>
     */
    public Optional<User> addCalendarToUser(Long idUser, Long idCalendar){
        //recuperiamo l'oggetto da eliminare tramite l'id
        Optional<User> userOptional = getUserFromId(idUser);
        //controlliamo che l'oggetto sia presente
        if(userOptional.isPresent()){
            //se presente recuperiamo l'oggetto da aggiungere tramite l'id
            Optional<Calendar> calendarOptional = calendarService.getCalendarById(idCalendar);
            //controlliamo se l'oggetto da aggiungere sia presente
            if(calendarOptional.isPresent()) {
                //se presente controlliamo se l'oggetto che vogliamo aggiungere non sia già presente nella lista
                if(!userOptional.get().getCalendarList().contains(calendarOptional.get())) {
                    //se non presente effettuiamo il collegamento con l'oggetto da aggiungere e l'oggetto a cui vogliamo aggiungerlo
                    calendarOptional.get().setUser(userOptional.get());
                    //salviamo l'oggetto da aggiungere
                    calendarService.addCalendar(calendarOptional.get());
                    //in fine ritorniamo il nostro oggetto
                    return userOptional;
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