

<style>
.nav-side-menu {
  overflow: auto;
  font-family: verdana;
  font-size: 15px;
  line-height: 50px;
  font-weight: 250;
  background-color: #f2f2f2;
  position: relative;
  top: 0px;
  height: 100%;
  color: #000000;
  text-indent: 10px;
}
.nav-side-menu .brand {
  background-color: #cccccc;
  line-height: 50px;
  font-weight: 250;
  display: block;
  text-align: center;
  font-size: 16px;
}

.nav-side-menu ul,
.nav-side-menu li {
  list-style: none;
  padding: 2px;
  margin: 1px;
  line-height: 35px;
  cursor: pointer;
}

.nav-side-menu ul .active,
.nav-side-menu li .active {
  border-left: 3px solid #808080;
  background-color: #e6e6e6;
}

.nav-side-menu li {
  padding-left: 0px;
  border-left: 3px solid #2e353d;
  border-bottom: 1px solid #23282e;
}
.nav-side-menu li a {
  text-decoration: none;
  color: #000000;
}

.nav-side-menu li:hover {
  border-left: 3px solid #808080;
  background-color: #e6e6e6;
}

/*
@media (max-width: 767px) {
  .nav-side-menu {
    position: relative;
    width: 100%;
    margin-bottom: 10px;
  }
  .nav-side-menu .toggle-btn {
    display: block;
    cursor: pointer;
    position: absolute;
    right: 10px;
    top: 10px;
    z-index: 10 !important;
    padding: 3px;
    background-color: #ffffff;
    color: #000000;
    width: 40px;
    text-align: center;
  }
  .brand {
    text-align: left !important;
    font-size: 22px;
    padding-left: 20px;
    line-height: 50px !important;
  }
}
@media (min-width: 767px) {
  .nav-side-menu .menu-list .menu-content {
    display: block;
  }
}
body {
  margin: 0px;
  padding: 0px;
}
*/

</style>
    <?php
        if(isset($_SESSION["logged_in"]))
        {
    ?>
    <div class="nav-side-menu col-md-2">
             <div class="brand">Dashboard</div>
                    <div class="menu-list">
                        <ul class="menu-content">
                          <!-- <li><a href="listcourses.php">Courses</a> </li> -->
                          <li><a href="notification.php">Notification</a> </li>
                          <li><a href="adduser.php">Add User</a> </li>
                        </ul>
  <?php
        }
?>

                    </div>
</div>
