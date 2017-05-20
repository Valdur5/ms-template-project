######### ms-ws module #########

# What should be in THIS module:

* All the Controllers to create the REST web services.
* The Integration tests for testing the REST web services.
* The RAML file for defining the REST contract.
* The Data Transfer Objects (DTO) since we don't want to expose out real business objects with all the database details to the public.
* Dozer mapping logic for mapping our Entity Models into DTO objects.

# What should NOT be in THIS module:

* All stuff from the other modules.
