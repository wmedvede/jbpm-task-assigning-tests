package com.myspace.task_assignments;

import org.kie.api.task.TaskEvent;
import org.kie.api.task.model.Group;
import org.kie.api.task.model.OrganizationalEntity;
import org.kie.api.task.model.PeopleAssignments;
import org.kie.api.task.model.Status;
import org.kie.api.task.model.Task;
import org.kie.api.task.model.TaskData;
import org.kie.api.task.model.User;

public class OptaPlannerTaskListener implements org.kie.api.task.TaskLifeCycleEventListener {

    public OptaPlannerTaskListener() {
        
    }
    
    @Override
    public void beforeTaskActivatedEvent(TaskEvent event) {

    }

    @Override
    public void beforeTaskClaimedEvent(TaskEvent event) {

    }

    @Override
    public void beforeTaskSkippedEvent(TaskEvent event) {

    }

    @Override
    public void beforeTaskStartedEvent(TaskEvent event) {

    }

    @Override
    public void beforeTaskStoppedEvent(TaskEvent event) {

    }

    @Override
    public void beforeTaskCompletedEvent(TaskEvent event) {

    }

    @Override
    public void beforeTaskFailedEvent(TaskEvent event) {

    }

    @Override
    public void beforeTaskAddedEvent(TaskEvent event) {

    }

    @Override
    public void beforeTaskExitedEvent(TaskEvent event) {

    }

    @Override
    public void beforeTaskReleasedEvent(TaskEvent event) {

    }

    @Override
    public void beforeTaskResumedEvent(TaskEvent event) {

    }

    @Override
    public void beforeTaskSuspendedEvent(TaskEvent event) {

    }

    @Override
    public void beforeTaskForwardedEvent(TaskEvent event) {

    }

    @Override
    public void beforeTaskDelegatedEvent(TaskEvent event) {

    }

    @Override
    public void beforeTaskNominatedEvent(TaskEvent event) {

    }

    @Override
    public void afterTaskActivatedEvent(TaskEvent event) {
        log("afterTaskActivatedEvent", event);
    }

    @Override
    public void afterTaskClaimedEvent(TaskEvent event) {
        log("afterTaskClaimedEvent", event);
    }

    @Override
    public void afterTaskSkippedEvent(TaskEvent event) {
        log("afterTaskSkippedEvent", event);
    }

    @Override
    public void afterTaskStartedEvent(TaskEvent event) {
        log("afterTaskStartedEvent", event);
    }

    @Override
    public void afterTaskStoppedEvent(TaskEvent event) {
        log("afterTaskStoppedEvent", event);
    }

    @Override
    public void afterTaskCompletedEvent(TaskEvent event) {
        log("afterTaskCompletedEvent", event);
    }

    @Override
    public void afterTaskFailedEvent(TaskEvent event) {
        log("afterTaskFailedEvent", event);
    }

    @Override
    public void afterTaskAddedEvent(TaskEvent event) {
        log("afterTaskAddedEvent", event);
    }

    @Override
    public void afterTaskExitedEvent(TaskEvent event) {
        log("afterTaskExitedEvent", event);
    }

    @Override
    public void afterTaskReleasedEvent(TaskEvent event) {
        log("afterTaskReleasedEvent", event);
    }

    @Override
    public void afterTaskResumedEvent(TaskEvent event) {
        log("afterTaskResumedEvent", event);
    }

    @Override
    public void afterTaskSuspendedEvent(TaskEvent event) {
        log("afterTaskSuspendedEvent", event);
    }

    @Override
    public void afterTaskForwardedEvent(TaskEvent event) {
        log("afterTaskForwardedEvent", event);
    }

    @Override
    public void afterTaskDelegatedEvent(TaskEvent event) {
        log("afterTaskDelegatedEvent", event);
    }

    @Override
    public void afterTaskNominatedEvent(TaskEvent event) {
        log("afterTaskNominatedEvent", event);
    }

    public void log(String eventName, TaskEvent event) {
        Task task = event.getTask();
        if (task == null) {
            System.out.println("**** -> " + eventName + " task is not yet created");
            return;
        }
        TaskData taskData = task.getTaskData();

        System.out.println("**** -> " + eventName + "{" +
                                   "\n  userId (the performer of the current operation): " + event.getTaskContext().getUserId() +

                                   "\n  Task: (" + taskData.getProcessInstanceId() + ", " + task.getId() + ")" +
                                   "\n  name: " + task.getName() +
                                   "\n  createdOn: " + taskData.getCreatedOn() +
                                   "\n  activationTime: " + taskData.getActivationTime() +
                                   "\n  expirationTime: " + taskData.getExpirationTime() +
                                   "\n  priority: " + task.getPriority() +
                                   "\n  previousStatus: " + printStatus(taskData.getStatus()) +
                                   "\n  status: " + taskData.getStatus() +
                                   "\n  taskInitiator: " + printTaskInitiator(task.getPeopleAssignments()) +
                                   "\n  actualOwner: " + printUser(taskData.getActualOwner()) +
                                   "\n  peopleAssignments: " + printPeopleAssignments(task.getPeopleAssignments()) +
                                   "\n  taskInput: " + task.getTaskData().getTaskInputVariables() +

                                   "}");
    }

    static String printStatus(Status status) {
        if (status != null) {
            return status.name();
        }
        return null;
    }

    static String printUser(User user) {
        if (user != null) {
            return user.getId();
        }
        return null;
    }

    static String printTaskInitiator(PeopleAssignments peopleAssignments) {
        if (peopleAssignments != null && peopleAssignments.getTaskInitiator() != null) {
            return peopleAssignments.getTaskInitiator().getId();
        }
        return null;
    }

    static String printPeopleAssignments(PeopleAssignments peopleAssignments) {
        if (peopleAssignments == null) {
            return null;
        }
        
        StringBuilder value = new StringBuilder();
        value.append("[");
        value.append("potentialOwners: [");
        if (peopleAssignments != null && peopleAssignments.getPotentialOwners() != null) {
            for (OrganizationalEntity entity : peopleAssignments.getPotentialOwners()) {
                if (entity instanceof User) {
                    value.append(", user: " + entity.getId());
                }
                if (entity instanceof Group) {
                    value.append(", group: " + entity.getId());
                }
            }
        }
        value.append("]");
        value.append("businessAdministrators: [");
        if (peopleAssignments != null && peopleAssignments.getBusinessAdministrators() != null) {
            for (OrganizationalEntity entity : peopleAssignments.getBusinessAdministrators()) {
                if (entity instanceof User) {
                    value.append(", user: " + entity.getId());
                }
                if (entity instanceof Group) {
                    value.append(", group: " + entity.getId());
                }
            }
        }
        value.append("]");
        value.append("]");
        return value.toString();
    }
}
