package com.inso.MinecraftProject.graph;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Graph class responsible for managing all ModNode objects
 * in the mod dependency graph.
 */
public class Graph implements GraphI<ModNode> {

    /**
     * Internal map used to store all mod nodes in the graph.
     * Each node is identified by a unique key in the format modid@version.
     */
    private final Map<String, ModNode> nodes;

    /**
     * Constructs an empty Graph and initializes its internal storage.
     */
    public Graph() {
        this.nodes = new HashMap<>();
    }

    /**
     * Generates a unique key for a mod node using the format modid@version.
     *
     * @param modNode the mod node for which to generate the key
     * @return a unique key in the format modid@version
     * @throws IllegalArgumentException if the modNode is null or has invalid fields
     */
    @Override
    public String generateKey(ModNode modNode) throws IllegalArgumentException {
        if (modNode == null || modNode.getModId() == null || modNode.getVersion() == null || modNode.getModId().isBlank() || modNode.getVersion().isBlank()) {
            throw new IllegalArgumentException("ModNode, modId, and version must be valid.");
        }

        return modNode.getModId() + "@" + modNode.getVersion();
    }

    /**
     * Adds a mod node to the graph if it does not already exist.
     *
     * @param modNode the mod node to add
     * @return true if the node was added, false if it already exists
     */
    @Override
    public boolean addNode(ModNode modNode) {
        String key = generateKey(modNode);

        if (nodes.containsKey(key)) {
            return false;
        }

        nodes.put(key, modNode);
        return true;
    }

    /**
     * Removes a mod node from the graph using its key.
     *
     * @param key the node key in the format modid@version
     * @return true if the node was removed, false if it was not found
     */
    @Override
    public boolean removeNode(String key) {
        return nodes.remove(key) != null;
    }

    /**
     * Finds a mod node in the graph using its key.
     *
     * @param key the node key in the format modid@version
     * @return the ModNode if found, otherwise null
     */
    @Override
    public ModNode findNode(String key) {
        return nodes.get(key);
    }

    /**
     * Returns an iterator over all ModNode objects stored in the graph.
     *
     * @return an iterator of ModNode objects
     */
    @Override
    public Iterator<ModNode> iterator() {
        return nodes.values().iterator();
    }
}