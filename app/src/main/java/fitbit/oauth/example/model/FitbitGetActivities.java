package fitbit.oauth.example.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FitbitGetActivities {

    private List<FitbitActivitiesLogStep> activitiesLogSteps = new ArrayList<FitbitActivitiesLogStep>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The activitiesLogSteps
     */
    public List<FitbitActivitiesLogStep> getActivitiesLogSteps() {
        return activitiesLogSteps;
    }

    /**
     *
     * @param activitiesLogSteps
     * The activities-log-steps
     */
    public void setActivitiesLogSteps(List<FitbitActivitiesLogStep> activitiesLogSteps) {
        this.activitiesLogSteps = activitiesLogSteps;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}