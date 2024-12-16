# Project Title
Gestionale Vivai Forestali della Regione Piemonte

# Project Description
Il servizio permette la compilazione da parte dei soggetti richiedenti privati e pubblici l’inserimento e l’invio ai vivai regionali di domande di piantine forestali a titolo oneroso e gratuito e permette ai referenti dei vivai di assegnare le partite richieste e di tenere aggiornati gli inventari di frutti, semi e piante. Permette anche ai gestori il caricamento in anagrafica dei certificati principali di identità dei materiali di base utilizzati dai vivai regionali.
La Regione provvede direttamente alla gestione dei propri vivai, la cui attività è finalizzata alla produzione di piante forestali autoctone, controllate e certificate. 
L'assegnazione a pagamento delle piante prodotte è concessa a soggetti pubblici e privati, a prescindere dallo scopo e dalla localizzazione del terreno di messa a dimora. 
Le piante possono anche essere assegnate gratuitamente per la realizzazione in territorio piemontese di specifici obiettivi (ad es. interventi di educazione e didattica ambientale, sperimentazione e divulgazione sulle tematiche della biodiversità forestale, recupero ambientale, imboschimento, rinaturalizzazione e sistemazione del territorio, ricostituzione di boschi danneggiati da incendi e altre calamità naturali)
Il servizio è rivolto a tutti i soggetti privati e pubblici che debbano far pervenire ai vivai regionali una domanda di piantine, a titolo oneroso o a titolo gratuito. 
E’ inoltre rivolto ai funzionari dei 3 vivai regionali che attraverso il servizio prendono in carico le domande e procedono con le assegnazioni di partite.
E’ infine rivolto ai funzionari del Settore “Lavori in amministrazione diretta di sistemazione idraulico-forestale, di tutela del territorio e vivaistica forestale" della Regione Piemonte, che attraverso il servizio monitorano le assegnazioni di piantine da parte dei vivai e hanno la possibilità di gestire le anagrafiche, in particolare quella dei certificati principali di identità relativi ai materiali di base utilizzati nei vivai.


Il servizio propone una pagina di Accreditamento dove vengono riportati i dati identificativi dell’utente che sta entrando e dove viene richiesto di inserire dei recapiti di contatto (telefono e mail) e dove infine viene richiesto di prendere visione dell’informativa sulla privacy. 
Procedendo sulla home page applicativa il servizio visualizza ai soggetti privati e pubblici due possibilità di inserimento domanda:

* Inserire una nuova domanda per assegnazione gratuita di piantine 
* Inserire una nuova domanda per assegnazione onerosa di piantine (vendita) 

Il servizio permette di visualizzare l’elenco delle domande inserite in precedenza, con possibilità a seconda dello stato di ogni domanda, di sola consultazione o di accesso a funzionalità specifiche.
Al primo accesso l’elenco potrebbe essere vuoto.
Una volta inviata la domanda, il funzionario del vivaio la prende in carico e procede con l’assegnazione delle piantine, ovvero per ogni specie richiesta viene assegnata una partita di quella specie, con una certa quantità a seconda delle disponibilità. Il funzionario invia l’assegnazione sempre tramite applicativo e il richiedente, avvertito da una mail, può entrare nuovamente nell’applicativo e, nel caso di assegnazione onerosa, procedere o meno con l’accettazione dell’assegnazione che gli è stata inviata.
In caso di accettazione deve firmare il pdf che gli è stato inoltrato e reinoltrarlo al vivaio.
A questo punto, sempre nel caso di assegnazione onerosa, il richiedente può procedere direttamente al pagamento online (il servizio è integrato con la piattaforma PagoPA).
Una volta che si è proceduto al pagamento, è possibile andare in vivaio e ritirare le piantine: il funzionario procederà sul servizio a registrare il ritiro (caricando i riferimenti del documento di trasporto) e potrà chiudere la pratica.
Nel caso di assegnazione gratuita, il funzionario valuta se il richiedente ha i requisiti per poter effettuare questo tipo di domanda, e in caso positivo effettua come prima l’assegnazione di piantine.
Essa non deve essere accettata dal richiedente come accadeva per l’assegnazione onerosa, per cui una volta che il richiedente ha ricevuto l’assegnazione, può andare in vivaio a ritirare le piante.


# Getting Started
Il prodotto VIFO è diviso nelle seguenti componenti:

[VIFOFAPI](https://github.com/regione-piemonte/vifo/tree/master/vifoapi) (Componente di api rest per l'esposizione verso il frontend)
[VIFOBO](https://github.com/regione-piemonte/vifo/tree/master/vifobo) (Componente di back office)
[VIFOWCL](https://github.com/regione-piemonte/vifo/tree/master/vifowcl) (Componente di front-end angular)


# Prerequisites
I prerequisiti per l'installazione del prodotto sono
## Software

- AdoptOpenJDK v. 11
- Apache 2.4
- Wildfly v. 17.0.0
- PostgreSQL 9.6.10


# Versioning
Per la gestione del codice sorgente viene utilizzata Git. Per la gestione del versioning si fa riferimento alla metodologia [Semantic Versioning](https://semver.org/) 

# Copyrights
(C) Copyright 2023 Regione Piemonte

# License
Questo software è distribuito con licenza EUPL-1.2
Consultare il file EUPL v1_2 IT-LICENSE.txt e EUPL v1_2 EN-LICENSE.txt  per i dettagli sulla licenza.