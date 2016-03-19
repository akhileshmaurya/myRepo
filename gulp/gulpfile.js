var gulp = require('gulp'),
    filter = require('gulp-filter'),
    uglify = require('gulp-uglify'),
    rename = require('gulp-rename'),
	replace = require('gulp-replace'),
    concat = require('gulp-concat'),
    browserSync = require('browser-sync'),
	livereload = require('gulp-livereload'),
	cssmin = require('gulp-minify-css'),
	mainBowerFiles = require('main-bower-files'),
	 cache = require('gulp-cache'),
	 jshint = require('gulp-jshint'),
	 imagemin = require('gulp-imagemin'),
    del = require('del');

var reload = browserSync.reload;

var filterByExtension = function(extension){
  return filter(function(file){
    return file.path.match(new RegExp('\\.' + extension + '$'));
  });
};
var filterBySubPath = function(fragment){
  return filter(function(file){
    return file.path.match(new RegExp(fragment));
   });
};

gulp.task('vendor', function() {
  var mainFiles = mainBowerFiles();
  if(!mainFiles.length)
    return;
  var jsFilter = filterByExtension('js');
  var cssFilter = filterByExtension('css');
  gulp.src(mainFiles)
    .pipe(jsFilter)
    .pipe(concat('vendor.js'))
    .pipe(rename({suffix: '.min'}))
    .pipe(uglify())
    .pipe(gulp.dest('./dist'))
    .pipe(jsFilter.restore())
    .pipe(cssFilter)
    .pipe(concat('vendor.css'))
    .pipe(replace(/\.\.\/images/g, 'images'))
    .pipe(replace(/\.\.\/fonts/g, 'fonts'))
    .pipe(rename({suffix: '.min'}))
    //.pipe(minifycss())
    .pipe(gulp.dest('./dist'))
    .pipe(cssFilter.restore())
    .pipe(filterBySubPath('fonts'))
    .pipe(gulp.dest('./dist/fonts/'))
    .pipe(filterBySubPath('images'))
    .pipe(imagemin({ optimizationLevel: 5, progressive: true, interlaced: true }))
    .pipe(gulp.dest('./dist/images/'))
    ;
  return gulp.src('src/images/**/*')
});

gulp.task('scripts', function() {
  return gulp.src('src/js/*.js')
    .pipe(concat('app.js'))
    .pipe(gulp.dest('dist/js/'))
    .pipe(uglify())
    .pipe(rename({
      suffix: '.min'
    }))
    .pipe(gulp.dest('dist/js/'));
});

gulp.task('styles', function() {
  return gulp.src('src/css/*.css')
    .pipe(concat('app.css'))
    .pipe(gulp.dest('dist/css/'))
    .pipe(rename({
      suffix: '.min'
    }))
    .pipe(gulp.dest('dist/css/'));
});
gulp.task('main', function() {
  return gulp.src('src/**/*.html')
    .pipe(gulp.dest('dist/'));
});
gulp.task('clean', function(cb) {
    del(['dist'], cb)
});
gulp.task('img', function() {
  return gulp.src('src/img/**/*')
    .pipe(imagemin({ optimizationLevel: 5, progressive: true, interlaced: true }))
    .pipe(gulp.dest('dist/img/'));
});

gulp.task('default', ['clean'], function(){
  gulp.start( 'vendor','main','styles','img', 'scripts' );
});

gulp.task('serve', function() {
  browserSync({
    server: {
      baseDir: 'dist'
    }
  });
  gulp.watch('src/css/**/*.css', ['styles']);
  gulp.watch('src/js/**/*.js', ['scripts']);
  gulp.watch('src/**/*.html', ['main']);
  livereload.listen();
  gulp.watch(['dist/**']).on('change', livereload.changed);
});