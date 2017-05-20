######### ms-persistence module #########

# What should be in THIS module:

* All the classes handling the persistence. Including the Repositories.
* The models (Entity) which are be used by the business layer AND stored in the database.

# What should NOT be in THIS module:

* Models that are not going to be stored in the database, those models should be created in the business layer.
