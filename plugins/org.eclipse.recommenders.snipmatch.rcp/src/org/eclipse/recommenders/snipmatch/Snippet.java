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
package org.eclipse.recommenders.snipmatch;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.eclipse.recommenders.utils.Uuidable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.google.gson.annotations.SerializedName;

/**
 * This class represent a snippet. This is used to serialize and de-serialize
 * snippet with gson.
 */
public class Snippet implements Uuidable, Comparable<Snippet> {

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
    private transient File location;

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

    @Override
    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public List<String> getAliases() {
        // XXX TODO no real aliases yet
        //-> Include all the aliases that are added through the editor
        Builder<String> b = ImmutableList.builder();
        return b.add(getName()).add(getDescription()).addAll(aliases).build();
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int compareTo(Snippet other) {
        UUID otherUuid = other.uuid;
        if (otherUuid == null || uuid == null) {
            return -1;
        }
        return otherUuid.compareTo(uuid);
    }

    public File getLocation() {
        return location;
    }

    protected void setLocation(File f) {
        location = f;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    public void setName(String name){
        this.name=name;
    }
    
    public void setAliases(List<String> aliases){
        this.aliases.clear();
        this.aliases.addAll(aliases);
        System.out.println(this.aliases);
    }
    
}
