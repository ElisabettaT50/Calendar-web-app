Obiettivo

Sviluppare un’applicazione backend per un sistema di gestione del Calendario simile a Google
Calendar.

Funzionalità di base

● Il sistema deve supportare più utenti: ogni utente può vedere solo i propri calendari.

● Deve essere possibile creare più calendari per lo stesso utente (ad esempio, il
calendario privato e quello di lavoro).

● Deve essere possibile creare eventi che abbiano le seguenti caratteristiche:

○ Sono associati ad uno e un solo calendario

○ Hanno una data, un’ora di inizio e una di fine, un titolo.
○ Possono durare più giorni

○ BONUS: possono avere degli invitati, e compariranno anche nei calendari dei
loro invitati

● Deve essere presente una funzione per creare eventi che si ripetono: ad es. tutti i giorni
fino ad una certa data, o in determinati giorni della settimana tutte le settimane, ecc.
(prendere ispirazione da Google Calendar)

● Deve essere possibile vedere i propri eventi:

○ Del giorno corrente o di un giorno qualsiasi

○ Della settimana corrente o di una settimana qualsiasi

○ Del mese corrente o di un mese qualsiasi

Parte 1

Abbozzare uno schema del programma, definendo:

● Le funzioni principali che l’API dovrà esporre

● Le entità principali del programma e le relazioni tra di esse.

È possibile creare un documento di Google Drive, un documento Word, inserire disegni
realizzati con tools online come https://app.diagrams.net/ o anche realizzati a mano libera.
L’obiettivo è riuscire ad avere un’idea di massima dell’architettura del programma prima di
iniziare a programmare, e riuscire a realizzare un piccolo documento di progetto.

Parte 2

Inizializzare un nuovo progetto Spring con le dependencies necessarie.
Creare i packages tipici del modello MVC (controller, service, model e interface) e realizzare le
funzionalità CRUD per le entità principali.

Parte 3

Definire le relazioni tra le entità, avendo cura di gestire nel modo corretto laddove si tratta di
one-to-one, one-to-many o many-to-many.

Parte 4

Realizzare le funzioni API per l’esecuzione delle operazioni più complesse:

- La creazione di nuovi calendari ed eventi deve gestire in modo corretto le associazioni
tra le entità create

- Le modifiche e le cancellazioni devono propagarsi correttamente alle entità collegate
  
- Invito di partecipanti all’evento con conseguente aggiunta al loro calendario
BONUS: invio di email di invito

- Creazione di eventi con ripetizione
  
