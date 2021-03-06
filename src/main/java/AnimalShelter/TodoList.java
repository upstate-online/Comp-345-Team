package AnimalShelter;

import java.util.List;
import java.util.*;

public class TodoList implements ToDoListAPI{

    int taskCount;
    //List<Task> toDoList;
    Collection<Task> toDoList;


    public TodoList() {
        this.taskCount = 0;
        this.toDoList = new PriorityQueue<>();

    }

    public String check(){
        String tasks = "";
        if(toDoList.isEmpty()){
            tasks = "The list is empty";
            System.out.println(tasks);
        }
        else{
            int i = 1;
            System.out.println("Item\t  Priority\t  Done\n");
            for(Task t : toDoList) {
                tasks = tasks + t.getTask() + "\n";
                System.out.println(i + "\t" + t.getTask() + "\t" + t.getPriority() + "\t" + t.getDone());
                i++;
            }

        }
        return tasks;
    }
    public void addTask(Task taskIn) {
        this.taskCount += 1;
        toDoList.add(taskIn);
    }

    public void removeTask(String taskToRemove) throws TaskException {
        for (Object taskToLookAt : toDoList) {
            Task t = (Task) taskToLookAt;

            if (t.getTask().compareTo(taskToRemove) == 0) {
                    toDoList.remove(t);
                    this.taskCount -= 1;
            throw new TaskException("Task does not exist");
            }
        }
    }

    public void markTaskDone(String taskToFind) {
        for (Object taskToLookAt : toDoList) {
            Task t = (Task) taskToLookAt;

            if (t.getTask().equals(taskToFind)) {
                t.markDone();
            //throw exception task not in list/ or sting not found
            }
        }
    }

    public Task updateTask(String taskToUpdate, String newTaskName, int newPriority) throws TaskException {
        boolean taskInList = false;
        Iterator itr = toDoList.iterator();

        while(itr.hasNext()){
            Object o = itr.next();
            Task t = (Task) o;
            if (t.getTask().equals(taskToUpdate)) {
                taskInList = true;
                toDoList.remove(t);

                Task newTask = new Task (newPriority,newTaskName);
                //t.setTask(newTaskName);
                //t.setPriority(newPriority);
                //toDoList.add(newTask);
                return newTask;
            }
        }

        if(!taskInList){
            throw new TaskException("Task is not in To-Do list");
        }
        return null;
    }




    public String getToDoList() {
            Iterator itr = toDoList.iterator();
            int i  = 1;

            String totalList = "";
//            for (int i = 0; i < taskCount; i++) {
//                totalList += (i + 1) + ". " + toDoList.get(i).getTask() + "\n";
//            }
            while(itr.hasNext()){
                Object o = itr.next();
                Task t = (Task) o; //cast as Task
                totalList += (i) + ". " + t.getTask() + "\n";
                i++;

            }

            return totalList;
        }


        public void fromJson(List listin){
            Iterator itr = listin.iterator();

            while(itr.hasNext()){
                Task tIn = (Task) itr.next(); //cast Object as Task
                this.toDoList.add(tIn);
            }

        }
}
