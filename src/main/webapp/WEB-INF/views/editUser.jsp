<div class="modal fade" id="editUser" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Edit User</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form id="folder-form" method="POST" action="/addUser">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-xs-5" for="user-name">Name:</label>
                        <input id="user-name" autofocus name="name" path="name" maxlength="30" />
                    </div>
                    <div class="form-group">
                        <label class="col-xs-5" for="user-id">User Id:</label>
                        <input path="user-id" id="user-id" name="id" value="${id}"/>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-5" for="user-surname" >Surname:</label>
                        <input path="user-surname" id="user-surname" name="surname"/>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-5" for="user-email" >Email:</label>
                        <input path="user-email" id="user-email" name="email"/>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-5" for="user-nickname" >Nickname:</label>
                        <input path="user-nickname" id="user-nickname" name="nickname"/>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-5" for="user-password" >Password:</label>
                        <input path="user-password" id="user-password" name="password"/>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-5" for="user-userRole" >Role:</label>
                        <select id="user-userRole" name="userRole" path="user-userRole">
                            <option>User</option>
                            <option>Admin</option>
                            <option>Moderator</option>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" id="submit-btn" class="btn btn-primary">Save</button>
                </div>
            </form>
        </div>
    </div>
</div>