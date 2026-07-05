package com.linearpast.ae2allinone.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public final class BlacklistMatcher {
    private final Set<ResourceLocation> exactIds;
    private final Set<ResourceLocation> tags;

    private BlacklistMatcher(Set<ResourceLocation> exactIds, Set<ResourceLocation> tags) {
        this.exactIds = exactIds;
        this.tags = tags;
    }

    public static BlacklistMatcher of(List<? extends String> entries) {
        Set<ResourceLocation> exactIds = new HashSet<>();
        Set<ResourceLocation> tags = new HashSet<>();
        if (entries != null) {
            for (String raw : entries) {
                if (raw == null) {
                    continue;
                }
                String entry = raw.trim();
                if (entry.isEmpty()) {
                    continue;
                }
                boolean isTag = entry.startsWith("#");
                String location = isTag ? entry.substring(1) : entry;
                ResourceLocation rl = ResourceLocation.tryParse(location);
                if (rl == null) {
                    continue;
                }
                if (isTag) {
                    tags.add(rl);
                } else {
                    exactIds.add(rl);
                }
            }
        }
        return new BlacklistMatcher(exactIds, tags);
    }

    public boolean hasTags() {
        return !tags.isEmpty();
    }

    public boolean isBlacklisted(ResourceLocation id) {
        return id != null && exactIds.contains(id);
    }

    public boolean isTaggedBlacklisted(Stream<? extends TagKey<?>> tagStream) {
        return tagStream.anyMatch(tag -> tags.contains(tag.location()));
    }
}
