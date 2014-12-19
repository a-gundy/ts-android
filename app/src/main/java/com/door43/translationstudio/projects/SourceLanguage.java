package com.door43.translationstudio.projects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by joel on 12/15/2014.
 */
public class SourceLanguage extends Language {
//    private String mVariant;
    private final int mDateModified;
    private Map<String, Resource> mResourceMap = new HashMap<String, Resource>();
    private List<Resource> mResources = new ArrayList<Resource>();
    private String mSelectedResourceId;

    public SourceLanguage(String code, String name, Direction direction,  int dateModified) {
        super(code, name, direction);
//        if(variant != null && variant.isEmpty()) variant = null;
//        mVariant = variant;
        mDateModified = dateModified;
    }

    /**
     * Adds a resource slug to the language
     * @param r
     */
    public void addResource(Resource r) {
        if(!mResourceMap.containsKey(r.getId())) {
            mResourceMap.put(r.getId(), r);
            mResources.add(r);
        }
    }

    /**
     * Returns an array of all the resources in this language
     * @return
     */
    public Resource[] getResources() {
        return mResourceMap.values().toArray(new Resource[]{});
    }

    /**
     * Returns the combination of language code and variant name.
     * If there is no variant then just the language id is returned.
     * @return
     */
//    public String getVariantId() {
//        if(mVariant != null) {
//            return getId() + "_" + mVariant;
//        } else {
//            return getId();
//        }
//    }

    /**
     * Returns the timestamp when the language was last modified
     * @return
     */
    public int getDateModified() {
        return mDateModified;
    }

    /**
     * Returns a resource by id
     * @param id the resoruce id
     * @return null if the resource does not exist.
     */
    public Resource getResource(String id) {
        if(mResourceMap.containsKey(id)) {
            return mResourceMap.get(id);
        } else {
            return null;
        }
    }

    /**
     * Returns a resource by id
     * @param index the resource index
     * @return null if the resource does not exist
     */
    public Resource getResource(int index) {
        if(index < mResources.size() && index >= 0) {
            return mResources.get(index);
        } else {
            return null;
        }
    }

    /**
     * Sets the currently selected resource in the language by id
     * @param id the resource id
     * @return true if the resource exists
     */
    public boolean setSelectedResource(String id) {
        Resource r = getResource(id);
        if(r != null) {
            mSelectedResourceId = r.getId();
        }
        return r != null;
    }

    /**
     * Sets the currently selected resource in the language by index
     * @param index
     * @return
     */
    public boolean setSelectedResource(int index) {
        Resource r = getResource(index);
        if(r != null) {
            mSelectedResourceId = r.getId();
        }
        return r != null;
    }

    /**
     * Returns the currently selected resource in the language
     * @return
     */
    public Resource getSelectedResource() {
        Resource selectedResource = getResource(mSelectedResourceId);
        if(selectedResource == null) {
            // auto select the first resource if no other resource has been selected.
            int defaultResourceIndex = 0;
            setSelectedResource(defaultResourceIndex);
            return getResource(defaultResourceIndex);
        } else {
            return selectedResource;
        }
    }
}
