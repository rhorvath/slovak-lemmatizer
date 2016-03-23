# slovak-lemmatizer
Slovak lemmatizer REST service implementation. It combines both dictionary 
approach and special set of rules approach to find correct base forms.

## rest endpoints
Two endpoints are available, both accessible by `GET` and `POST` methods. 
In the case of a `POST` request, text for lemmatization is expected in the post 
body and not in the url parameter `text`.

### fast lemmatization
Returns a text with the best lemmas found.

    /fast?text=?&useDatabase=?&useTvaroslovnik=?&inputDiacritics=?&outputDiacritics=?&keepStructure=?

### full lemmatization
Returns a json array with all the lemmas found.

    /full?text=?&useDatabase=?&useTvaroslovnik=?&inputDiacritics=?&outputDiacritics=?

## requirements
 * [Morphological database from Slovak Academy of Science](http://korpus.juls.savba.sk/morphology_database.html) imported into database
 * Tvaroslovnik project 

## todos or missing features
 * integration of Tvaroslovnik(lemmatization using special set of rules)
 * automation of morphological database import
 