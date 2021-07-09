# ![](src/main/resources/titolo.png)
Autore: Francesco Musci

# Introduzione
Il seguente materiale costituisce la documentazione per il progetto per l'esame di Metodi Avanzati di Programmazione (2021). Il progetto, volto ad utilizzare i concetti e i metodi appresi durante il corso, consiste in un videogioco di tipo avventura grafica o testuale. In questo caso, si tratta di un'avventura grafica.

"Talos Dynasty" è un'avventura grafica ispirata a [Myst](https://it.wikipedia.org/wiki/Myst) e ambientata nel mondo di [The Elder Scrolls V: Skyrim](https://it.wikipedia.org/wiki/The_Elder_Scrolls_V:_Skyrim).
La scelta di questi due videogiochi non è casuale. In primo luogo perché il gameplay del primo e l'ambientazione del secondo si prestano bene ad essere riutilizzati per creare un gioco con Java SWING; in secondo luogo perché l'autore apprezza molto questi due titoli.

Il gioco inizia con lo Jarl (un "sindaco" nell'universo di Skyrim) che ti parla di un grave furto avvenuto in città. Il tuo compito è quello di risolvere il mistero e restituire il maltolto.

## Tutorial
Avviata l'applicazione, nella schermata iniziale si potrà scegliere se iniziare una nuova partita o caricarne una in corso.

**Nuova partita**: inizierà una nuova partita, previo inserimento del nome del personaggio. Nel caso in cui non sia inserito un nome, verrà visualizzata una schermata di errore. Nel caso in cui sia già presente un nuovo salvataggio, viene mostrata una schermata che chiede all'utente se vuole sovrascrivere il precedente salvataggio o meno.

**Carica partita**: carica una partita salvata. Se non è presente alcuna partita salvata da caricare, verrà mostrato un messaggio d'errore.

Iniziato il gioco, per interagire con l'ambiente circostante è sufficiente cliccarci. Per muoversi, invece, si dovrà cliccare ai bordi dello schermo: bordo superiore per andare avanti in una direzione; bordi sinistro e destro per voltare il personaggio nella rispettiva direzione.

In alto a sinistra, il pulsante **Salva ed esci** salva il gioco e chiude il processo. In alto a destra, un menù a tendina contiene l'inventario del personaggio; il pulsante **Esamina** consente di esaminare l'oggetto selezionato nel menù a tendina.

# Architettura del sistema
Il progetto implementa il pattern architetturale *Model-View-Presenter*. La logica di gioco (*Model*) e l'interfaccia che la implementa (*View*) non possono interagire tra loro in maniera diretta: il *Presenter* è un mediatore che si occupa di prendere in input i comandi e i dati della *View* e inviarli al *Model*, e viceversa. Questa architettura garantisce il rispetto di uno degli obiettivi dell'object-oriented design: la presentazione separata.

Il sistema è progettato per essere esteso e semplice da utilizzare per un eventuale utente che lo estenderà per progettare il suo gioco. Nel progetto, la classe `TalosDynasty` estende la classe `GameController` per usufruirne e per aggiungere ulteriori funzionalità.

Particolare è la creazione dell'intefaccia **iteratore circolare**. Si tratta di un iteratore in cui l'elemento che segue l'ultimo elemento è il primo elemento e l'elemento che precede il primo elemento è l'ultimo elemento. Nel progetto è presente l'interfaccia `CircularIterator` e anche una sua possibile implementazione: `CircularArrayList`.

## Architettura dei package e delle classi

### Package `view`

* `GameGUI`: l'interfaccia di gioco.

### Package `model`

Contiene le classi centrali al funzionamento del sistema.

* `AdventureGame`: interfaccia che descrive il comportamento che dovranno avere le classi che realizzano un gioco di questo tipo.
* `Dialogue`: contiene i metodi per popolare, leggere e connettersi a un database SQL mediante la libreria H2.
* `GameMap`: contiene la mappa del gioco e i metodi per crearla e navigarci.
* `GameController`: implementa l'interfaccia `AdventureGame` contiene la logica principale del gioco. Aggrega le altre classi del package `model` e le coordina, implementando così il *Façade* design pattern, ovvero fornendo un'interfaccia semplice e unificata a fronte di un sistema complesso.

### Package `game`

Contiene classi aggiuntive che implementano funzionalità proprie del gioco.

* `Enemy`: combattimento finale.
* `MinigameJabberClient`: client per il minigioco numerico.
* `MinigameJabberServer`: server per il minigioco numerico.
* `TalosDynasty`: classe principale che si occupa di interfacciarsi con le altre classi e di istanziarle. Anche qui viene implementato il *Façade* design pattern.

### Package `util`

Contiene la descrizione della struttura dati "iteratore circolare", che consente di iterare ciclicamente su una lista di elementi (ovvero: l'elemento che segue l'ultimo elemento è il primo elemento; l'elemento che precede il primo elemento è l'ultimo elemento).

* `CircularIterator`: interfaccia per la struttura dati "iteratore circolare".
* `CircularArrayList`: implementazione di `CircularIterator` che si avvale della struttura dati preesistente `ArrayList`, estendendola.

## Diagramma UML delle classi
![](src/main/resources/UML.png)

# Dettagli implementativi
