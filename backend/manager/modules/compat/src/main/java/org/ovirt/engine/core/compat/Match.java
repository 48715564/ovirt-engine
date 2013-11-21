package org.ovirt.engine.core.compat;

import java.util.ArrayList;
import java.util.regex.MatchResult;

// I believe this is a wrapper for java.util.regex.MatchResult
/**
 * @deprecated Use {@link MatchResult} directly instead.
 */
@Deprecated
public class Match {
    private String Value;
    private boolean Success;
    private ArrayList<Match> Groups;

    public Match(MatchResult mr, boolean success) {
        Groups = new ArrayList<Match>();
        if (success) {
            this.Success = success;
            for (int x = 1; x <= mr.groupCount(); x++) {
                Groups.add(new Match(mr.group(x)));
            }
            Value = mr.group();
        } else {
            Value = null;
        }
    }

    public Match(String value) {
        this.Value = value;
        this.Success = true;
    }

    public String getValue() {
        return Value;
    }

    public ArrayList<Match> groups() {
        return Groups;
    }

    public boolean success() {
        return Success;
    }
}
