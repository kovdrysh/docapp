<div class="modal fade" id="addUser" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Create User</h5>
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
                        <label class="col-xs-5" for="user-surname">Username:</label>
                        <input  id="user-surname" autofocus name="surname" path="surname" maxlength="30" />
                    </div>
                    <div class="form-group">
                        <label class="col-xs-5" for="user-email">Email:</label>
                        <input  id="user-email" autofocus name="email" path="email" maxlength="30" />
                    </div>
                    <div class="form-group">
                        <label class="col-xs-5" for="user-nickname">Login Name:</label>
                        <input id="user-nickname" autofocus name="nickname" path="nickname" maxlength="30" />
                    </div>
                    <div class="form-group">
                        <label class="col-xs-5" for="user-password">Password:</label>
                        <input id="user-password" autofocus name="password" path="password" maxlength="30" />
                    </div>
                    <div class="form-group">
                        <label class="col-xs-5" for="user-role">User Role</label>
                        <select id="user-role" autofocus name="userRole"  path="user-role">
                            <option>User</option>
                            <option>Admin</option>
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