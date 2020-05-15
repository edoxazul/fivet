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

package cl.ucn.disc.pdis.fivet.model;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


/**
 * The Persona class.
 *
 * @author Eduardo Alvarez S.
 */
@DatabaseTable(tableName = "persona")
public final class PersonaMod {

/**
     * The id.
     */
    @DatabaseField(generatedId = true)
    private Long id;

    /**
     * The Nombre.
     */
    @DatabaseField(canBeNull = false)
    private String nombre;

    /**
     * The Apellido.
     */
    @DatabaseField(canBeNull = false)
    private String apellido;

    /**
     * The Rut.
     */
    @DatabaseField(canBeNull = false, index = true)
    private String rut;

    /**
     * Empty contructor.
     */
    PersonaMod() {
        // nothing here.
    }

    /**
     * Constructor de una persona.
     * - El nombre no puede ser null.
     * - El nombre debe tener al menos 2 letras.
     * - El apellido no puede ser null.
     * - El apellido debe tener al menos 3 letras.
     * - El rut no puede ser null.
     * - El rut debe ser valido.
     *
     * @param nombre   a utilizar.
     * @param apellido a usar.
     * @param rut      valido.
     */
    public PersonaMod(String nombre, String apellido, String rut) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.rut = rut;

    }

    /**
     * @return the id.
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the apellido.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @return the rut.
     */
    public String getRut() {
        return rut;
    }

    /**
     * @return the nombre and apellido.
     */
    public String getNombreApellido() {
        return nombre + " " + apellido;
    }

}
