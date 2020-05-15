/*
 * Copyright [2020] [Eduardo Alvarez S]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cl.ucn.disc.pdis.fivet.dao;


import cl.ucn.disc.pdis.fivet.model.PersonaMod;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class StorageTest {

    private static final Logger log = LoggerFactory.getLogger(StorageTest.class);

    /**
     * Testing the ORMLite + H2 (database)
     */
    @Test
    public void testDatabase(){

        // The database to use (in RAM memory)
        String dataBaseURL = "jdbc:h2:mem:fivet_db";

        // Connection source: autoclose with the try/catch
        try (ConnectionSource connectionSource = new JdbcConnectionSource(dataBaseURL)) {

            //Create the table from Persona Class Annotations
            TableUtils.createTableIfNotExists(connectionSource, PersonaMod.class);

            //Dao of Persona
            Dao<PersonaMod, Long> daoPersona = DaoManager.createDao(connectionSource,PersonaMod.class);

            //The Persona
            PersonaMod persona = new PersonaMod("Matias","Fernandez","221592875");

            //insert Persona into the database
            int tuples = daoPersona.create(persona);
            log.debug("ID: {}",persona.getId());
            //
            Assertions.assertEquals(1,tuples,"Save tuples != 1");

            //Get from db
            PersonaMod personaDb = daoPersona.queryForId(persona.getId());

            Assertions.assertEquals(persona.getNombre(),personaDb.getNombre(),"Names are equals?");
            Assertions.assertEquals(persona.getApellido(),personaDb.getApellido(),"Surname are equals?");
            Assertions.assertEquals(persona.getRut(),personaDb.getRut(),"Ruts are equals?");

            //Search by rut: Select * FROM  'persona' WHERE 'rut' = '221592875'
            List<PersonaMod> personaList = daoPersona.queryForEq("rut","221592875");
            Assertions.assertEquals(persona.getRut(),personaDb.getRut(),"Ruts are equals?");

            //Not found rut
            Assertions.assertEquals(0,daoPersona.queryForEq("rut","19").size(),"Found somethings?");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
