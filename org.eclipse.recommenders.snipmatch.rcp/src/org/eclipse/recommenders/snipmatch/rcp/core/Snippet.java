/**
 * Copyright (c) 2013 Madhuranga Lakjeewa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Madhuranga Lakjeewa - initial API and implementation.
 */
package org.eclipse.recommenders.snipmatch.rcp.core;

import java.util.List;
import java.util.UUID;

//import org.eclipse.recommenders.utils.Uuidable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.google.gson.annotations.SerializedName;

/**
 * This class represent a snippet. This is used to serialize and de-serialize
 * snippet with gson.
 */
public class Snippet implements Comparable<Snippet> {

    @SerializedName("uuid")
    private UUID uuid;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("aliases")
    private List<String> aliases;
    @SerializedName("code")
    private String code;

    public Snippet(UUID uuid, String name, String description,
            List<String> aliases, String code) {
        super();
        this.uuid = uuid;
        this.name = name;
        this.description = description;
        this.aliases = aliases;
        this.code = code;
    }

    protected Snippet() {
        // used for deserialization
    }

    /*
     * @Override public UUID getUuid() { return uuid; }
     */

    public String getName() {
        return name;
    }

    public List<String> getAliases() {
        // XXX TODO no real aliases yet
        Builder<String> b = ImmutableList.builder();
        return b.add(getName()).add(getDescription()).build();
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Used to set the code for the snippet at serialization.
     * 
     * @param code
     *            The new code
     */
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public int compareTo(Snippet other) {
        return other.uuid.compareTo(uuid);
    }
}
